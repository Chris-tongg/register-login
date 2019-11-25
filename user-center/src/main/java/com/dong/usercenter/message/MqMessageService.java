package com.dong.usercenter.message;

import com.dong.usercenter.domain.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author dong
 * @since JDK1.8
 */
@Component
@Slf4j
public class MqMessageService {

    @Value("${rocketmq.user-topic-register}")
    private String userTopicRegister;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendRegisterMsg(UserDto userDto){
        rocketMQTemplate.asyncSend(userTopicRegister, userDto, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.debug("发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                log.debug("发送成功");
            }
        });
    }
}
