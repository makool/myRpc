package com.zjd;

import com.zjd.handler.RpcClientProxy;
import com.zjd.idl.domain.pojo.Message;
import com.zjd.idl.service.MessageService;
import com.zjd.idl.service.PersonService;
import com.zjd.idl.service.SaleService;
import org.junit.Test;

/**
 * @Description TODO
 * @Author zhengjiongda
 * @Date 2021/1/22 16:17
 * @Version 1.0
 **/
public class RpcClientTest {

    @Test
    public void rpcTest() {
        RpcClientProxy proxy = new RpcClientProxy();
        MessageService messageService = proxy.getProxy(MessageService.class);
        Message str = messageService.getMessage("title", "内容");
        System.out.println(str);
    }
}
