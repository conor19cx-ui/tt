package org.example.week11.task;

import jakarta.mail.MessagingException;
import org.example.week11.dto.MailDTO;
import org.example.week11.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务：每年固定时间自动发送生日祝福邮件
 */
@Component
public class BirthdayScheduledTask {

    @Autowired
    private MailService mailService;

    /**
     * 🔥 Cron 表达式：每年 10月01日 10:00:00 发送邮件
     * 格式：秒 分 时 日 月 周 年(可选)
     * 修改示例：
     * 每年 5月20日 9点30分 → 0 30 9 20 5 ?
     * 每年 1月1日 0点0分 → 0 0 0 1 1 ?
     */
    @Scheduled(cron = "0 * * * * ?")
    public void sendBirthdayMailScheduled() throws MessagingException {
        // 1. 创建邮件对象
        MailDTO mailDTO = new MailDTO();
        // 2. 收件人邮箱（改成你要发送的邮箱）
        mailDTO.setTo("3561926155@qq.com");
        // 3. 邮件主题
        mailDTO.setSubject("🎂 定时生日祝福邮件");

        // 4. 生日祝福HTML内容（和你第二个任务完全一致）
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
            <p>祝你岁岁平安，万事胜意！</p>
            <p>来自定时任务的自动祝福~</p>
            </div>
            </body>
            </html>
            """;
        mailDTO.setContent(html);

        // 5. 调用富文本邮件方法发送
        mailService.sendHtmlMail(mailDTO);
        System.out.println("✅ 定时任务执行成功：生日邮件已发送！");
    }
}