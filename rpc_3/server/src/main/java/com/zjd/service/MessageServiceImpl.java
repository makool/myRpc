package com.zjd.service;

import com.zjd.idl.domain.pojo.Message;
import com.zjd.idl.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @Description TODO
 * @Author zhengjiongda
 * @Date 2021/1/22 15:45
 * @Version 1.0
 **/
@Service("com.zjd.idl.service.MessageService")
public class MessageServiceImpl implements MessageService {

    @Override
    public Message getMessage(String title, String content) {
        Message message = new Message();
        message.setUuid(String.valueOf(UUID.randomUUID()).replace("-",""));
        message.setGmtCreate(new Date());
        message.setContent(content);
        message.setTitle(title);
        return message;
    }
}
