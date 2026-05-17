package org.example.week11.service;

import org.example.week11.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    // 任务1：普通文本邮件
    public void sendSimpleMail(MailDTO mailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(mailDTO.getTo());
        message.setSubject(mailDTO.getSubject());
        message.setText(mailDTO.getContent());
        mailSender.send(message);
    }

    // 任务2：富文本生日邮件
    public void sendHtmlMail(MailDTO mailDTO) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom(fromEmail);
        helper.setTo(mailDTO.getTo());
        helper.setSubject(mailDTO.getSubject());
        helper.setText(mailDTO.getContent(), true);
        mailSender.send(mimeMessage);
    }

    // 任务3：带附件邮件（完美适配你的文件）
    public void sendMailWithAttachment(MailDTO mailDTO, String[] filePaths) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 关键：开启附件模式，必写true
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(mailDTO.getTo());
        helper.setSubject(mailDTO.getSubject());
        helper.setText(mailDTO.getContent(), true);

        // 自动添加所有附件
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (file.exists()) {
                helper.addAttachment(file.getName(), file);
                System.out.println("✅ 附件添加成功：" + file.getName());
            }
        }
        mailSender.send(mimeMessage);
    }
}