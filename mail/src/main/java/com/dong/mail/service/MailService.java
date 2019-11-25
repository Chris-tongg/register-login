package com.dong.mail.service;

import javax.mail.MessagingException;

/**
 * @author dong
 * @since JDK1.8
 */
public interface MailService {
    void sendMail(String target,String content) throws MessagingException;
}
