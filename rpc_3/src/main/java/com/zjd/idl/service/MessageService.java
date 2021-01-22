package com.zjd.idl.service;

import com.zjd.idl.domain.pojo.Message;

/**
 * @Description TODO
 * @Author zhengjiongda
 * @Date 2021/1/22 15:44
 * @Version 1.0
 **/
public interface MessageService {

    Message getMessage(String title, String content);
}
