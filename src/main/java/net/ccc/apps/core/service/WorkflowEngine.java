//package net.ccc.apps.core.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import net.ccc.apps.core.service.dto.*;
//import net.ccc.apps.core.service.mapper.AppUserMapper;
//import net.ccc.apps.fabricationmanag.domain.Form;
//import net.ccc.apps.fabricationmanag.service.FormService;
//import org.slf4j.Logger;
//import java.time.Instant;
//import java.util.Optional;
//import org.slf4j.LoggerFactory;
//import net.ccc.apps.core.domain.*;
//import java.util.stream.Collectors;
//import net.ccc.apps.core.repository.*;
//import tech.jhipster.service.QueryService;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Service;
//import org.springframework.data.domain.Pageable;
//import net.ccc.apps.core.service.mapper.InboxMapper;
//import net.ccc.apps.core.domain.enumeration.FormType;
//import net.ccc.apps.core.domain.enumeration.FormStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import net.ccc.apps.core.service.queryService.InboxQueryService;
//
//
//@Service
//@Transactional
//public class WorkflowEngine {
//    private final Logger log = LoggerFactory.getLogger(WorkflowTemplateService.class);
//
//    @Autowired
//    protected AppUserMapper appUserMapper;
//    @Autowired
//    protected AppUserService appUserService;
//    @Autowired
//    protected InboxMapper inboxMapper;
//    @Autowired
//    protected InboxService inboxService;
//    @Autowired
//    protected FormService formService;
//    @Autowired
//    protected RoleRepository roleRepository;
//    @Autowired
//    protected InboxRepository inboxRepository;
//    @Autowired
//    protected WorkflowStepService workflowStepService;
//    @Autowired
//    protected WorkflowStepRepository workflowStepRepository;
//    @Autowired
//    protected WorkflowProcessService workflowProcessService;
//    @Autowired
//    protected WorkflowProcessRepository workflowProcessRepository;
//    @Autowired
//    protected WorkflowActionUserService workflowActionUserService;
//    @Autowired
//    protected WorkflowTemplateRepository workflowTemplateRepository;
//
//    public WorkflowEngine( InboxMapper inboxMapper,
//                           InboxService inboxService,
//                           FormService formService,
//                           AppUserMapper appUserMapper,
//                           RoleRepository roleRepository,
//                           AppUserService appUserService,
//                           InboxRepository inboxRepository,
//                           WorkflowStepService workflowStepService,
//                           WorkflowStepRepository workflowStepRepository,
//                           WorkflowProcessService workflowProcessService,
//                           WorkflowProcessRepository workflowProcessRepository,
//                           WorkflowActionUserService workflowActionUserService,
//                           WorkflowTemplateRepository workflowTemplateRepository ) {
//        this.inboxMapper = inboxMapper;
//        this.inboxService = inboxService;
//        this.appUserMapper = appUserMapper;
//        this.roleRepository = roleRepository;
//        this.appUserService = appUserService;
//        this.formService = formService;
//        this.inboxRepository = inboxRepository;
//        this.workflowStepService = workflowStepService;
//        this.workflowStepRepository = workflowStepRepository;
//        this.workflowProcessService = workflowProcessService;
//        this.workflowProcessRepository = workflowProcessRepository;
//        this.workflowActionUserService = workflowActionUserService;
//        this.workflowTemplateRepository = workflowTemplateRepository;
//    }
//
//    public WorkflowProcessDTO createWorkflowProcess(long formID, String description, FormType formType, String email, String toEmail) {
//        WorkflowProcess preProcess = workflowProcessRepository.findByFormId(formID);
//        WorkflowProcessDTO process = new WorkflowProcessDTO();
//        process.setFormId(formID);
//        process.setFormType(formType);
//        process.setDescription(description);
//        process.setInitiationTime(Instant.now());
//        process.setStatus(String.valueOf(FormStatus.Submitted));
//        AppUser user = appUserService.findByEmail(email).get();
//        AppUser toUser = appUserService.findByEmail(toEmail).get();
//        process.setInitiatedByUserId(user.getUserId());
//        WorkflowProcessDTO processDTO= workflowProcessService.save(process);
//        createWorkFlowStep(processDTO.getId(), user, toUser,1, formID, formType);
//        return processDTO;
//    }
//
//    public WorkflowStepDTO createWorkFlowStep(long processId, AppUser user, AppUser toUser, long seq, long formID, FormType formType) {
//        WorkflowStepDTO step = new WorkflowStepDTO();
//        List<WorkflowTemplate> tempList = workflowTemplateRepository.findWorkFlowBySeqandType((int) seq, formType);
//        WorkflowTemplate template = tempList.get(0);
//        step.setDescription(template.getActionDescription());
//        step.setTriggerTime(Instant.now());
//        step.setActionLabel(template.getStepName());
//        step.setActionStatus(template.getSuccessStatus());
//        step.setSignoffRule(template.getSignOffRule());
//        step.setWorkflowProcessId(processId);
//        step.setStepInitiator(user.getUserId());
//        step.setWorkflowTemplateStepId(seq);
//        WorkflowStepDTO stepDTO = workflowStepService.save(step);
//        createStepActionUser(stepDTO.getId(), user);
//        createNotification(template, user, toUser, formID, formType);
//        return stepDTO;
//    }
//
//    public void createApproveStep(long formID, String email, String toEmail, FormType formType) {
//        List<WorkflowProcess> processes = workflowProcessRepository.findAll().stream().filter(process -> process.getFormId() == formID).collect(Collectors.toList());
//        if(!processes.isEmpty()){
//            WorkflowProcess process = processes.get(0);
//            long seq =  process.getWorkflowSteps().stream().count() +1;
//            process.getWorkflowSteps().forEach(step ->{
//                if(step.isComplete() == null){
//                    WorkflowStepDTO stepDTO = workflowStepService.findOne(step.getId()).get();
//                    stepDTO.setComplete(true);
//                    stepDTO.setCompleteTime(Instant.now());
//                    workflowStepService.partialUpdate(stepDTO);
//                }
//            } );
//             AppUser user = appUserService.findByEmail(email).get();
//             AppUser toUser = appUserService.findByEmail(toEmail).get();
//             updateReadTime(user, formID);
//             createWorkFlowStep(process.getId(), user, toUser, seq, formID, formType);
//        }
//    }
//
//    public void createReviseStep(long formID, String email, String toEmail, FormType formType) {
//        List<WorkflowProcess> processes = workflowProcessRepository.findAll().stream().filter(process -> process.getFormId() == formID).collect(Collectors.toList());
//        if(!processes.isEmpty()){
//            WorkflowProcess process = processes.get(0);
//            process.getWorkflowSteps().forEach(step -> {
//                if(step.isComplete() == null) {
//                    WorkflowStepDTO stepDTO = workflowStepService.findOne(step.getId()).get();
//                    stepDTO.setComplete(false);
//                    stepDTO.setCompleteTime(Instant.now());
//                    workflowStepService.partialUpdate(stepDTO);
//                }
//            });
//            AppUser user = appUserService.findByEmail(email).get();
//            AppUser toUser = appUserService.findByEmail(toEmail).get();
//            updateReadTime(user, formID);
//            reviseNotification(user, toUser, formID, formType);
//        }
//    }
//
//    public WorkflowActionUserDTO createStepActionUser(long workFlowId, AppUser user){
//        WorkflowActionUserDTO actionUser = new WorkflowActionUserDTO();
//        actionUser.setWorkflowStepId(workFlowId);
//        appUserService.findByEmail(UserService.currentLoggedInUser().getEmail())
//            .ifPresent(v -> {
//                actionUser.setActionUser(findAppuserByEmail(v.getEmail()));
//                actionUser.setActionUserId(v.getUserId());
//            });
//        return workflowActionUserService.save(actionUser);
//    }
//
//    public AppUserDTO findAppuserByEmail(String email){
//        List<AppUserDTO> appUser = appUserService.findAll().stream().filter(user -> user.getEmail() == email).collect(Collectors.toList());
//        if(appUser.stream().count() > 0){
//            return appUser.get(0);
//        }else return null;
//    }
//
//    public void updateReadTime(AppUser user, long formId){
//        inboxRepository.findAllByFormId(formId).forEach(inbox -> {
//            if(inbox.getAssignedTo().getUserId() == user.getUserId()){
//                inbox.setReadTime(Instant.now());
//                inbox.setUnread(false);
//                inboxRepository.save(inbox);
//            }
//        });
//    }
//
//    public void createNotification(WorkflowTemplate template, AppUser user, AppUser toUser, long formID, FormType formType){
//        InboxDTO inbox = new InboxDTO();
//        Form form = formService.findOne(formID).get();
//        if (user.getUserId() == null) {
//        }
//        if (toUser.getUserId() == null) {
//        }
//        inbox.setDateTime(Instant.now());
//        inbox.setFormId(formID);
//        inbox.setFormType(formType);
//        inbox.setTitle(template.getStepName());
//        inbox.setMessage(form.getFormName());
//        inbox.setUnread(true);
//        inbox.setDescription(template.getActionDescription());
//        inbox.setAssignedToId(toUser.getUserId());
//        inbox.setAssignedById(user.getUserId());
//        inboxService.save(inbox);
//    }
//
//    public void reviseNotification(AppUser user, AppUser toUser, long formID, FormType formType){
//        InboxDTO inbox = new InboxDTO();
//        Form form = formService.findOne(formID).get();
//        inbox.setDateTime(Instant.now());
//        inbox.setFormId(formID);
//        inbox.setFormType(formType);
//        inbox.setTitle("Revise");
//        inbox.setMessage(form.getFormName());
//        inbox.setUnread(true);
//        inbox.setDescription("");
//        inbox.setAssignedToId(toUser.getUserId());
//        inbox.setAssignedById(user.getUserId());
//        inboxService.save(inbox);
//    }
//
//}
