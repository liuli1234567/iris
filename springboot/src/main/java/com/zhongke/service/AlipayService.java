package com.zhongke.service;

import java.util.Map;

public interface AlipayService {
    Map create_pay(String auth_code,String out_trade_no);

}
