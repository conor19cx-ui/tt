package org.example.week11;

import org.example.week11.dto.MailDTO;
import org.example.week11.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    // 任务1：普通邮件
    @Test
    public void testSendSimpleMail() {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setTo("3561926155@qq.com");
        mailDTO.setSubject("任务1：普通文本邮件");
        mailDTO.setContent("老师，我完成了普通文本邮件发送！");
        mailService.sendSimpleMail(mailDTO);
    }

    // 任务2：生日富文本邮件
    @Test
    public void testSendBirthdayMail() throws Exception {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setTo("3561926155@qq.com");
        mailDTO.setSubject("🎂 任务2：生日祝福邮件");
        String html = """
            <!DOCTYPE html>
            <html>
            <head>
            <meta charset="UTF-8">
            <style>
            body{background:#fff5f5;padding:20px;}
            .card{max-width:600px;margin:0 auto;background:white;padding:30px;border-radius:10px;text-align:center;}
            </style>
            </head>
            <body>
            <div class="card">
            <div style='font-size:50px'>🎂</div>
            <h2 style='color:red'>生日快乐！</h2>
            <p>祝你天天开心！</p>
            </div>
            </body>
            </html>
            """;
        mailDTO.setContent(html);
        mailService.sendHtmlMail(mailDTO);
    }

    // 任务3：带附件邮件（你的3个文件全部适配！）
    @Test
    public void testSendAttachmentMail() throws Exception {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setTo("3561926155@qq.com");
        mailDTO.setSubject("📎 任务3：带附件的邮件");
        mailDTO.setContent("这是一封带附件的邮件，包含图片和PDF文件~");

        // 精准匹配你的文件：1.jpg、2.jpg、PDF（直接用，不用改）
        String[] files = {
                "1.jpg",
                "2.jpg",
                "iCampus・智慧校园生活 App 开发文档.pdf"
        };

        mailService.sendMailWithAttachment(mailDTO, files);
        System.out.println("✅ 带附件邮件发送成功！");
    }
}