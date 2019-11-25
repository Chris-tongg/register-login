package com.dong.mail.listener;

import com.dong.mail.domain.dto.UserDto;
import com.dong.mail.service.MailService;
import com.dong.mail.utils.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author dong
 * @since JDK1.8
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup= "user-group-dong",topic = "user-topic-register-dong")
public class MailListener implements RocketMQListener<UserDto> {
    private static final String ACTIVE_URL = "?code=";
    @Resource
    private MailService mailService;
    @Resource
    private RedisService redisService;
    @Value("${active.base-url}")
    private String baseUrl;

    @Override
    public void onMessage(UserDto userDto) {
        try {
            mailService.sendMail(userDto.getEmail(),getUrl(userDto.getUid()));
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
    }

    public String getUrl(int uid){
        String uuid = UUID.randomUUID().toString();
        redisService.setEx(uuid,String.valueOf(uid),10, TimeUnit.MINUTES);
        return baseUrl+ACTIVE_URL+uuid;
    }
}
