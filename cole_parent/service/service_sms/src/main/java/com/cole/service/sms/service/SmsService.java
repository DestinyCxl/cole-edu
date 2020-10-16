package com.cole.service.sms.service;

import com.netflix.client.ClientException;

/**
 * @author: Cxl
 * @since: 2020-10-02
 **/


public interface SmsService {

    void send(String mobile, String checkCode) throws ClientException, com.aliyuncs.exceptions.ClientException;
}
