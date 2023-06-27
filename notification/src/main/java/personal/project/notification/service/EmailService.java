package personal.project.notification.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import personal.project.notification.model.MailApprovalRequest;

@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendApprovalMail(MailApprovalRequest approvalRequest) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Map<String, Object> properties = new HashMap<>();
        properties.put("name", approvalRequest.getUserName());
        properties.put("subscriptionDate", LocalDate.now().toString());
        properties.put("documentNumber", approvalRequest.getDocumentNumber());
        properties.put("location", approvalRequest.getLocation());
        properties.put("vendorName", approvalRequest.getVendorName());

        Context context = new Context();
        context.setVariables(properties);

        mimeMessageHelper.setTo(approvalRequest.getTo());
        mimeMessageHelper.setFrom("abhinab21dutta@gmail.com");
        mimeMessageHelper.setSubject("APPROVAL MAIL");

        String htmlContent = templateEngine.process("template1.html", context);
        mimeMessageHelper.setText(htmlContent, true);

        log.info("Sending mail: {} \nWith HTML Body: {}", mimeMessage, htmlContent);
        javaMailSender.send(mimeMessage);
    }
}
