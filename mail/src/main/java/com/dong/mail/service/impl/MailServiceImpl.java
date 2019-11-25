package com.dong.mail.service.impl;

import com.dong.mail.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author dong
 * @since JDK1.8
 */
@Service
public class MailServiceImpl implements MailService {
    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.from-name}")
    private String fromName;

    @Override
    public void sendMail(String target, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        //发送目标
        helper.setTo(target);
        //发送内容
        helper.setText(content);
        //发送者
        helper.setFrom(fromName);
        //发送主题
        helper.setSubject("注册激活");
        javaMailSender.send(mimeMessage);
    }
}
