package net.ccc.apps.core.service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import net.ccc.apps.core.domain.*;
import net.ccc.apps.core.domain.enumeration.FormType;
import net.ccc.apps.core.domain.enumeration.SignoffOption;
import net.ccc.apps.core.domain.enumeration.WorkflowAction;
import net.ccc.apps.core.repository.*;
import net.ccc.apps.core.service.Workflows.manager.WorkflowProcessManager;
import net.ccc.apps.core.service.Workflows.workflows.IWorkflowProcess;
import net.ccc.apps.core.service.criteria.InboxCriteria;
import net.ccc.apps.core.service.criteria.WorkflowStepCriteria;
import net.ccc.apps.core.service.criteria.WorkflowTemplateCriteria;
import net.ccc.apps.core.service.dto.AppUserDTO;
import net.ccc.apps.core.service.dto.WorkflowActionUserDTO;
import net.ccc.apps.core.service.dto.WorkflowProcessFormDTO;
import net.ccc.apps.core.service.dto.WorkflowStepDTO;
import net.ccc.apps.core.service.mapper.AppUserMapper;
import net.ccc.apps.core.service.mapper.InboxMapper;
import net.ccc.apps.core.service.mapper.WorkflowActionUserMapper;
import net.ccc.apps.core.service.mapper.WorkflowStepMapper;
import net.ccc.apps.core.service.queryService.InboxQueryService;
import net.ccc.apps.core.service.queryService.WorkflowStepQueryService;
import net.ccc.apps.core.service.queryService.WorkflowTemplateQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

@Service
@Transactional
public class WorkflowProcessEngine {

    @Autowired
    protected AppUserService appUserService;

    @Autowired
    protected InboxRepository inboxRepository;

    @Autowired
    protected InboxMapper inboxMapper;

    @Autowired
    protected InboxQueryService inboxQueryService;

    @Autowired
    protected WorkflowStepQueryService workflowStepQueryService;

    @Autowired
    protected WorkflowTemplateQueryService workflowTemplateQueryService;

    @Autowired
    protected WorkflowProcessRepository workflowProcessRepository;

    @Autowired
    protected WorkflowStepRepository workflowStepRepository;

    @Autowired
    protected WorkflowTemplateRepository workflowTemplateRepository;

    @Autowired
    protected WorkflowActionUserRepository workflowActionUserRepository;

    @Autowired
    protected WorkflowStepMapper workflowStepMapper;

    @Autowired
    protected WorkflowActionUserMapper workflowActionUserMapper;

    @Autowired
    protected AppUserMapper appUserMapper;

    @Autowired
    WorkflowProcessManager workflowProcessManager;

    protected WorkflowStepDTO createFirstWorkflowStep(Long formId, FormType formType) {
        WorkflowTemplate workflowTemplate = getWorkflowTemplate(formType, 2);
        WorkflowProcess workflowProcess = createWorkflowProcess(formId, formType, workflowTemplate.getInitialStatus());
        WorkflowStep firstStep = createWorkflowStep(workflowTemplate);
        firstStep.setWorkflowProcess(workflowProcess);
        firstStep.setWorkflowTemplateStepId(workflowTemplate.getId());
        return workflowStepMapper.toDto(firstStep);
    }

    public WorkflowProcess execute(WorkflowStepDTO currentStep, WorkflowStepDTO nextStep, WorkflowProcessFormDTO workflowProcessForm) {
//        WorkflowProcess workflowProcess = workflowProcessRepository.findById(currentStep.getWorkflowProcessId()).get();
//        WorkflowTemplate workflowTemplate = workflowTemplateRepository.findById(currentStep.getWorkflowTemplateStepId()).get();
//        WorkflowStepDTO previousWorkflowStep = null;
//
//        WorkflowAction action = workflowProcessForm.getWorkflowAction();
//        String comment = workflowProcessForm.getComment();
//        Set<AppUserDTO> targetUsers = workflowProcessForm.getTargetUsers();
//        Long formId = workflowProcessForm.getId();
//
//        boolean stepComplete = false;
//        boolean approved = action.equals(WorkflowAction.Approved);
//        boolean isNew = currentStep.getId() == null;
//        Set<AppUserDTO> targetUsrs = approved || isNew
//            ? targetUsers
//            : new HashSet(Arrays.asList(appUserMapper.toDto(appUserService.findOne(currentStep.getStepInitiator()).get())));
//
//        if (isNew) {
//            Long stepId = workflowStepRepository.saveAndFlush(workflowStepMapper.toEntity(currentStep)).getId();
//            List<WorkflowActionUserDTO> actionUsers = createActionUsers(stepId, targetUsers, comment);
//            currentStep.setWorkflowActionUserDTOList(actionUsers);
//            workflowActionUserRepository.saveAll(workflowActionUserMapper.toEntity(actionUsers));
//        } else {
//            IWorkflowProcess manager = workflowProcessManager.getWorkflowProccessInstance(workflowProcess.getFormType());
//            previousWorkflowStep = getPreviousWorkflowStep(currentStep.getWorkflowTemplateStepId());
//            updateWorkFlowStepActionUser(comment, currentStep, approved);
//            String newStatus = approved ? workflowTemplate.getSuccessStatus() : previousWorkflowStep.getActionStatus();
//            workflowProcess.setStatus(newStatus);
//            stepComplete = !workflowTemplate.isMultipleActionUsers() || checkWorkflowProcessComplete(currentStep);
//            if (stepComplete) updateWorkFlowStep(newStatus, currentStep);
//
//            if (approved) {
//                boolean completed = manager != null && manager.getCompletedStatus().equals(currentStep.getActionStatus());
//                if (nextStep != null) {
//                    nextStep.setWorkflowProcessId(workflowProcess.getId());
//                    Long nextStepId = workflowStepRepository.save(workflowStepMapper.toEntity(nextStep)).getId();
//                    List<WorkflowActionUserDTO> actionUsers = createActionUsers(nextStepId, targetUsrs, null);
//                    nextStep.setWorkflowActionUserDTOList(actionUsers);
//                    workflowActionUserRepository.saveAll(workflowActionUserMapper.toEntity(actionUsers));
//                }
//                if (
//                    completed && !Objects.equals(workflowTemplate.getInitialStatus(), workflowTemplate.getSuccessStatus())
//                ) manager.onSuccess(workflowProcessForm);
//            } else {
//                previousWorkflowStep.setWorkflowProcessId(workflowProcess.getId());
//                Long previousWorkflowStepId = workflowStepRepository.save(workflowStepMapper.toEntity(previousWorkflowStep)).getId();
//                List<WorkflowActionUserDTO> actionUsers = createActionUsers(previousWorkflowStepId, targetUsrs, null);
//                previousWorkflowStep.setWorkflowActionUserDTOList(actionUsers);
//                workflowActionUserRepository.saveAll(workflowActionUserMapper.toEntity(actionUsers));
//            }
//
//            if (stepComplete || currentStep.getSignoffRule() == SignoffOption.Any) inboxRepository.deleteAll(
//                inboxRepository.findAllByFormId(formId)
//            ); else {
//                appUserService
//                    .findByEmail(UserService.currentLoggedInUser().getEmail())
//                    .ifPresent(v -> {
//                        inboxRepository.delete(getCurrentUserInbox(formId, v.getUserId()));
//                    });
//                //  inboxRepository.delete(getCurrentUserInbox(formId,UserService.currentLoggedInUser().getEmail()));
//            }
//        }
//
//        sendNotifications(approved || isNew ? currentStep : previousWorkflowStep, workflowProcess, targetUsrs);
//
       return null;
    }

    public Inbox getCurrentUserInbox(Long formId, String assignedUser) {
        AtomicReference<Inbox> inbox = new AtomicReference<Inbox>();
        InboxCriteria inboxCriteria = new InboxCriteria();
        LongFilter formIdFilter = new LongFilter();
        formIdFilter.setEquals(formId);
        inboxCriteria.setFormId(formIdFilter);
        StringFilter assignedToFilter = new StringFilter();
        assignedToFilter.setEquals(assignedUser);
        inboxCriteria.setAssignedToId(assignedToFilter);
        inboxQueryService.findByCriteria(inboxCriteria).stream().findFirst().ifPresent(val -> inbox.set(inboxMapper.toEntity(val)));
        return inbox.get();
    }

    public boolean checkWorkflowProcessComplete(WorkflowStepDTO currentStep) {
        AtomicBoolean complete = new AtomicBoolean(true);
        if (!currentStep.getSignoffRule().equals(SignoffOption.Any)) currentStep
            .getWorkflowActionUserDTOList()
            .stream()
            .filter(actionUser -> actionUser.isApprove() == null)
            .findAny()
            .ifPresent(val -> complete.set(false));
        return complete.get();
    }

    public WorkflowProcess execute(WorkflowStepDTO workflowStep, WorkflowStepDTO nextStep, FormType formType, WorkflowProcessFormDTO form) {
        if (workflowStep == null) workflowStep = createFirstWorkflowStep(form.getId(), formType);
        return execute(workflowStep, nextStep, form);
    }

    public WorkflowStep getNextWorkflowStep(Long currentTemplateId) {
        AtomicReference<WorkflowTemplate> nextWorkflowTemplate = new AtomicReference<>();
        WorkflowTemplate currentWorkflowTemplate = workflowTemplateRepository.findById(currentTemplateId).get();
        Integer currentSequence = currentWorkflowTemplate.getSequence();
        WorkflowTemplateCriteria criteria = new WorkflowTemplateCriteria();
        IntegerFilter seqFilter = new IntegerFilter();
        seqFilter.setGreaterThan(currentSequence);
        WorkflowTemplateCriteria.FormTypeFilter formTypeFilter = new WorkflowTemplateCriteria.FormTypeFilter();
        formTypeFilter.setEquals(currentWorkflowTemplate.getFormType());
        BooleanFilter enabledFilter = new BooleanFilter();
        enabledFilter.setEquals(true);
        criteria.setEnabled(enabledFilter);
        criteria.setFormType(formTypeFilter);
        criteria.setSequence(seqFilter);
        workflowTemplateRepository
            .findAll(workflowTemplateQueryService.createSpecification(criteria))
            .stream()
            .min(Comparator.comparing(WorkflowTemplate::getSequence))
            .ifPresent(wfTemplate -> nextWorkflowTemplate.set(wfTemplate));
        WorkflowStep nextStep = createWorkflowStep(nextWorkflowTemplate.get());
        return nextStep;
    }

    public WorkflowStepDTO getPreviousWorkflowStep(Long currentTemplateId) {
        WorkflowTemplate currentWorkflowTemplate = workflowTemplateRepository.findById(currentTemplateId).get();
        Integer currentSequence = currentWorkflowTemplate.getSequence();
        AtomicReference<WorkflowTemplate> previousWorkflowTemplate = new AtomicReference<>();
        WorkflowTemplateCriteria criteria = new WorkflowTemplateCriteria();
        IntegerFilter seqFilter = new IntegerFilter();
        seqFilter.setLessThan(currentSequence);
        WorkflowTemplateCriteria.FormTypeFilter formTypeFilter = new WorkflowTemplateCriteria.FormTypeFilter();
        formTypeFilter.setEquals(currentWorkflowTemplate.getFormType());
        BooleanFilter enabledFilter = new BooleanFilter();
        enabledFilter.setEquals(true);
        criteria.setEnabled(enabledFilter);
        criteria.setFormType(formTypeFilter);
        criteria.setSequence(seqFilter);
        workflowTemplateRepository
            .findAll(workflowTemplateQueryService.createSpecification(criteria))
            .stream()
            .max(Comparator.comparing(WorkflowTemplate::getSequence))
            .ifPresent(val -> previousWorkflowTemplate.set(val));
        WorkflowStep previousStep = createWorkflowStep(previousWorkflowTemplate.get());
        return workflowStepMapper.toDto(previousStep);
    }

    public WorkflowTemplate getWorkflowTemplate(FormType formType, Integer sequence) {
        AtomicReference<WorkflowTemplate> wfTemplate = new AtomicReference<>();
        WorkflowTemplateCriteria criteria = new WorkflowTemplateCriteria();
        WorkflowTemplateCriteria.FormTypeFilter formTypeFilter = new WorkflowTemplateCriteria.FormTypeFilter();
        formTypeFilter.setEquals(formType);
        IntegerFilter sequenceFilter = new IntegerFilter();
        sequenceFilter.setEquals(sequence);
        criteria.setSequence(sequenceFilter);
        criteria.setFormType(formTypeFilter);
        workflowTemplateRepository
            .findAll(workflowTemplateQueryService.createSpecification(criteria))
            .stream()
            .findFirst()
            .ifPresent(val -> wfTemplate.set(val));
        return wfTemplate.get();
    }

    public WorkflowStep getCurrentWorkflowStep(Long formId) {
        AtomicReference<WorkflowStep> step = new AtomicReference<>();
        WorkflowProcess workflowProcess = workflowProcessRepository.findByFormId(formId);
        if (workflowProcess != null) {
            WorkflowStepCriteria criteria = new WorkflowStepCriteria();
            LongFilter workflowProcessIdFilter = new LongFilter();
            workflowProcessIdFilter.setEquals(workflowProcess.getId());
            criteria.setWorkflowProcessId(workflowProcessIdFilter);
            workflowStepRepository
                .findAll(workflowStepQueryService.createSpecification(criteria))
                .stream()
                .max(Comparator.comparing(WorkflowStep::getId))
                .ifPresent(val -> step.set(val));
            return step.get();
        }
        return null;
    }

    public WorkflowStep createWorkflowStep(WorkflowTemplate workflowTemplate) {
        if (workflowTemplate == null) return null;
        WorkflowStep workflowStep = new WorkflowStep();
        workflowStep.setDescription(workflowTemplate.getActionDescription());
        workflowStep.setActionStatus(workflowTemplate.getInitialStatus());
        workflowStep.setActionLabel(workflowTemplate.getStepName());
        appUserService
            .findByEmail(UserService.currentLoggedInUser().getEmail())
            .ifPresent(v -> {
                workflowStep.setStepInitiator(v.getUserId());
            });
        //workflowStep.setStepInitiator(UserService.currentLoggedInUser().getEmail());
        workflowStep.setSignoffRule(workflowTemplate.getSignOffRule());
        workflowStep.setWorkflowTemplateStepId(workflowTemplate.getId());
        workflowStep.setTriggerTime(Instant.now());
        workflowStep.setComplete(false);
        return workflowStep;
    }

    public WorkflowProcess createWorkflowProcess(Long formId, FormType formType, String status) {
        WorkflowProcess workflowProcess = new WorkflowProcess();
        workflowProcess.setFormId(formId);
        workflowProcess.setFormType(formType);
        workflowProcess.setInitiationTime(Instant.now());
        workflowProcess.setInitiatedBy(appUserService.findByEmail(UserService.currentLoggedInUser().getEmail()).orElse(null));
        workflowProcess.setStatus(status);
        return workflowProcessRepository.saveAndFlush(workflowProcess);
    }

    private WorkflowStep updateWorkFlowStep(String newStatus, WorkflowStepDTO workflowStep) {
        if (workflowStep != null) {
            workflowStep.setActionStatus(newStatus);
            workflowStep.setComplete(true);
            workflowStep.setCompleteTime(Instant.now());
            return workflowStepRepository.save(workflowStepMapper.toEntity(workflowStep));
        }
        return null;
    }

    public void updateWorkFlowStepActionUser(String comment, WorkflowStepDTO workflowStep, boolean approve) {
        if (workflowStep != null) {
            AtomicReference<String> userId = new AtomicReference<>();
            appUserService
                .findByEmail(UserService.currentLoggedInUser().getEmail())
                .ifPresent(v -> {
                    userId.set(v.getUserId());
                });
            WorkflowActionUserDTO actionUser = workflowStep
                .getWorkflowActionUserDTOList()
                .stream()
                .filter(user -> user.getActionUser().getUserId().equals(userId.get()))
                .findFirst()
                .orElse(null);
            if (actionUser != null) {
                actionUser.setActionTime(Instant.now());
                actionUser.setApprove(approve);
                actionUser.setComment(comment);
                actionUser.setWorkflowStepId(workflowStep.getId());
                workflowActionUserRepository.save(workflowActionUserMapper.toEntity(actionUser));
            }
        }
    }

    public List<WorkflowActionUserDTO> createActionUsers(Long stepId, Set<AppUserDTO> targetUsers, String comment) {
        List<WorkflowActionUserDTO> actionUsers = new ArrayList<>();
        for (AppUserDTO actionUser : targetUsers) {
            WorkflowActionUserDTO workflowActionUser = new WorkflowActionUserDTO();
            workflowActionUser.setActionUser(actionUser);
            workflowActionUser.setComment(comment);
            workflowActionUser.setWorkflowStepId(stepId);
            actionUsers.add(workflowActionUser);
        }
        return actionUsers;
    }

    public void sendNotifications(WorkflowStepDTO workflowStep, WorkflowProcess workflowProcess, Set<AppUserDTO> appUsers) {
        if (appUsers == null) return;
        List<Inbox> newInboxes = new ArrayList<Inbox>();
        for (AppUserDTO appUser : appUsers) {
            AppUser loggedInUser = appUserService.findByEmail(UserService.currentLoggedInUser().getEmail()).orElse(null);
            Inbox inbox = new Inbox();
            inbox.assignedBy(loggedInUser);
            inbox.assignedTo(appUserMapper.toEntity(appUser));
            inbox.setDateTime(Instant.now());
            inbox.setUnread(true);
            inbox.setTitle(workflowStep.getActionLabel());
            inbox.setMessage(workflowStep.getDescription());
            inbox.setDescription(workflowProcess.getDescription());
            inbox.setFormId(workflowProcess.getFormId());
            inbox.setFormType(workflowProcess.getFormType());
            newInboxes.add(inbox);
        }
        inboxRepository.saveAll(newInboxes);
    }
}
