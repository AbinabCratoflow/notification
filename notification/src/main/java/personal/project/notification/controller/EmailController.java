package personal.project.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import personal.project.notification.model.MailApprovalRequest;
import personal.project.notification.service.EmailService;

@Slf4j
public class EmailController {
    @Autowired
    EmailService service;

    @PostMapping(value = "/send/approval/mail/single")
    @ResponseBody
    String approvalMail(@RequestBody MailApprovalRequest approvalRequest) throws MessagingException {
        try {
            service.sendApprovalMail(approvalRequest);
        } catch (Exception exception) {
            log.info("Error in Sending Message!\nABORTED!!!");
            return "ABORTED!";
        }
        return "MAIL SENT\nPORT TERMINATED FOR FURTHER USE";
    }
}
