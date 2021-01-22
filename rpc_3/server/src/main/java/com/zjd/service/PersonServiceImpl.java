package com.zjd.service;

import com.zjd.idl.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author zhengjiongda
 * @Date 2021/1/16 16:51
 * @Version 1.0
 **/
@Service("com.zjd.idl.service.PersonService")
public class PersonServiceImpl implements PersonService {
    @Override
    public String say() {
        return "hello person";
    }
}
