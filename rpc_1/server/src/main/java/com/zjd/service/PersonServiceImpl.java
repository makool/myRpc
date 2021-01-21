package com.zjd.service;

import com.zjd.idl.PersonService;

/**
 * @Description TODO
 * @Author zhengjiongda
 * @Date 2021/1/16 16:51
 * @Version 1.0
 **/
public class PersonServiceImpl implements PersonService {
    @Override
    public String say() {
        return "hello person";
    }
}
