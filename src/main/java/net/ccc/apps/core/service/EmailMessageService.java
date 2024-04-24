package net.ccc.apps.core.service;


import net.ccc.apps.core.domain.EmailMessage;
import net.ccc.apps.core.repository.EmailMessageRepository;
import net.ccc.apps.core.service.dto.EmailMessageDTO;
import net.ccc.apps.core.service.mapper.EmailMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Service Implementation for managing {@link EmailMessage}.
 */
@Service
@Transactional
public class EmailMessageService {

    private final Logger log = LoggerFactory.getLogger(EmailMessageService.class);

    private final EmailMessageRepository emailMessageRepository;

    private final EmailMessageMapper emailMessageMapper;


    private final JavaMailSender emailSender;

    @Value("${email.test-recipient}")
    protected String testRecipient;


    @Value("${email.enable}")
    protected boolean enableToSendEmail;

    @Value("${email.maxSendTimes}")
    protected Long maxSendTimes;



    public EmailMessageService(EmailMessageRepository emailMessageRepository, EmailMessageMapper emailMessageMapper, JavaMailSender emailSender) {
        this.emailMessageRepository = emailMessageRepository;
        this.emailMessageMapper = emailMessageMapper;
        this.emailSender = emailSender;


    }

    /**
     * Save a emailMessage.
     *
     * @param emailMessageDTO the entity to save.
     * @return the persisted entity.
     */
    public EmailMessageDTO save(EmailMessageDTO emailMessageDTO) {
        log.debug("Request to save EmailMessage : {}", emailMessageDTO);
        EmailMessage emailMessage = emailMessageMapper.toEntity(emailMessageDTO);
        emailMessage = emailMessageRepository.save(emailMessage);
        return emailMessageMapper.toDto(emailMessage);
    }

    /**
     * Save a emailMessage.
     *
     * @param emailMessage the entity to save.
     * @return the persisted entity.
     */
    public EmailMessageDTO saveEmailMessage(EmailMessage emailMessage) {
        log.debug("Request to save EmailMessage : {}", emailMessage);

        emailMessage = emailMessageRepository.save(emailMessage);
        return emailMessageMapper.toDto(emailMessage);
    }

    /**
     * Partially update a emailMessage.
     *
     * @param emailMessageDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EmailMessageDTO> partialUpdate(EmailMessageDTO emailMessageDTO) {
        log.debug("Request to partially update EmailMessage : {}", emailMessageDTO);

        return emailMessageRepository
            .findById(emailMessageDTO.getId())
            .map(
                existingEmailMessage -> {
                    emailMessageMapper.partialUpdate(existingEmailMessage, emailMessageDTO);
                    return existingEmailMessage;
                }
            )
            .map(emailMessageRepository::save)
            .map(emailMessageMapper::toDto);
    }

    /**
     * Get all the emailMessages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EmailMessageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EmailMessages");
        return emailMessageRepository.findAll(pageable).map(emailMessageMapper::toDto);
    }

    /**
     * Get one emailMessage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmailMessageDTO> findOne(Long id) {
        log.debug("Request to get EmailMessage : {}", id);
        return emailMessageRepository.findById(id).map(emailMessageMapper::toDto);
    }

    /**
     * Get one emailMessage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmailMessage> findEmailMessage(Long id) {
        log.debug("Request to get EmailMessage : {}", id);
        return emailMessageRepository.findById(id);
    }


    /**
     * Delete the emailMessage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EmailMessage : {}", id);
        emailMessageRepository.deleteById(id);
    }

//     @Transactional
//    public List<EmailMessage> sendBulkOfEmails()throws MessagingException, IOException, TemplateException{
//        log.info("Sending Email with Freemarker HTML Template Example");
//        List<EmailMessage> emailsToSent = emailMessageRepository.findByIsSentAndEmailRecipientEmailIsNotNull(false)
//            .stream()
//            .limit(5).collect(Collectors.toList());
//
//        List< MimeMessage> messagesList= new ArrayList<>();
//
//        emailsToSent.forEach(email->{
//            MimeMessage message = emailSender.createMimeMessage();
//            MimeMessageHelper helper = null;
//            try {
//                helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                    StandardCharsets.UTF_8.name());
//                helper.setText(email.getContent(), true);
//                helper.setSubject(email.getSubject()!=null?email.getSubject():"");
//                helper.setFrom("LMS-No Reply<lmsExch@ccc.net>");
//                MimeMessageHelper finalHelper = helper;
//
//                email.getRecipients().forEach(recipient-> {
//                    try {
//                        if (this.testRecipient!=null && !this.testRecipient.isEmpty()) {
//                            finalHelper.setTo(recipient.getName() + "<" + this.testRecipient + ">");
//                        }
//                        else{
//                            finalHelper.setTo(recipient.getName() + "<" + recipient.getEmail() + ">");
//                        }
//                    } catch (MessagingException e) {
//                        e.printStackTrace();
//                    }
//                });
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//
//            messagesList.add(message);
//
//            email.setIsSent(true);
//            email.setSentTime(Instant.now());
//
//        });
//        List<EmailMessage> emailsToSentList = emailMessageRepository.saveAll(emailsToSent);
//
//        MimeMessage[] mimeArray = new MimeMessage[messagesList.size()];
//        mimeArray = messagesList.toArray(mimeArray);
//
//         Arrays.stream(mimeArray).forEach(mime->{
//             try {
//                 emailSender.send(mime);
//                 TimeUnit.MILLISECONDS.sleep(500);
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });
//
//
//        return emailsToSentList;
//    }

//    @Autowired
//    InboxService inboxService;
//    public List<MimeMessage> resendAcknowledgedEmail()throws MessagingException, IOException, TemplateException {
//        log.info("Sending Email with Freemarker HTML Template Example");
//        List<EmailMessage> emailsToSent = emailMessageRepository.sendEmployeeAcknowledgementEmails(maxSendTimes)
//            .stream()
//
//    //        .limit(5)
//            .collect(Collectors.toList());
//
//        List<MimeMessage> messagesList = new ArrayList<>();
//        emailsToSent.forEach(email -> {
//            MimeMessage  message=this.createMessage(email);
//           if(email.getSendTimes()==null) {
//               messagesList.add(message);
//               email.setSendTimes(1L);
//           }else {
//               if (!(email.getSendTimes() == maxSendTimes)) {
//                   messagesList.add(message);
//                   email.setIsSent(false);
//                   email.setSentTime(null);
//                   email.setSendTimes(email.getSendTimes() + 1);
//               } else {
//                   if (email.getSendTimes() == (maxSendTimes)) {
//                       LeaveRequestDTO lrDTO = leaveRequestService.findOne(email.getReferenceId()).get();
//                       inboxService.deleteAll(inboxService.findByReferenceId(lrDTO.getId().toString()));
//                       leaveRequestService.systemLeaveFormalizationWFlowStep(lrDTO);
//                       leaveRequestService.addHR_FormalizationEmail(lrDTO, lrDTO.getEmployee(), "System_Acknowledge");
//                   }
//               }
//           }});
//        emailMessageRepository.saveAll(emailsToSent);
//       return messagesList;
//    }

    public MimeMessage createMessage(EmailMessage email){
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
            helper.setText(email.getContent(), true);
            helper.setSubject(email.getSubject() != null ? email.getSubject() : "");
            helper.setFrom("MTReq-No Reply<MTReq@ccc.net>");
            MimeMessageHelper finalHelper = helper;

            email.getRecipients().forEach(recipient -> {
                try {

                    if (this.testRecipient!=null && !this.testRecipient.isEmpty()) {
                        finalHelper.setTo(recipient.getName() + "<" + this.testRecipient + ">");
                    }
                    else{
                        finalHelper.setTo(recipient.getName() + "<" + recipient.getEmail() + ">");
                    }


                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }



}
