package com.zjd.service;

import com.zjd.idl.service.SaleService;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author zhengjiongda
 * @Date 2021/1/21 22:09
 * @Version 1.0
 **/
@Service("com.zjd.idl.service.SaleService")
public class SaleServiceImpl implements SaleService {

    @Override
    public String giveDiscount(Integer number) {
        return "今天商城里面的东西全部打" + number + "折";
    }
}
