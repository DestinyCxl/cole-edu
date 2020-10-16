package com.cole.service.trade.service;

import java.util.Map;

/**
 * @author: Cxl
 * @since: 2020-10-12
 **/
public interface WeixinPayService {
    Map<String, Object> createNative(String orderNo, String remoteAddr);
}