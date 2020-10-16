package com.cole.service.trade.service.impl;

import com.cole.common.base.result.ResultCodeEnum;
import com.cole.common.base.util.ExceptionUtils;
import com.cole.common.base.util.HttpClientUtils;
import com.cole.service.base.exception.ColeExcepiton;
import com.cole.service.trade.entity.Order;
import com.cole.service.trade.service.OrderService;
import com.cole.service.trade.service.WeixinPayService;
import com.cole.service.trade.util.WeixinPayProperties;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Cxl
 * @since: 2020-10-12
 **/
@Service
@Slf4j
public class WeixinPayServiceImpl implements WeixinPayService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WeixinPayProperties weixinPayProperties;

    @Override
    public Map<String, Object> createNative(String orderNo, String remoteAddr) {
        try{
            //根据课程订单号获取订单
            Order order = orderService.getOrderByOrderNo(orderNo);

            //调用微信api接口：统一下单（支付订单）
            HttpClientUtils client = new HttpClientUtils("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //组装接口参数
            Map<String, String> params = new HashMap<>();
            //关联的公众号的appid
            params.put("appid", weixinPayProperties.getAppId());
            //商户号
            params.put("mch_id", weixinPayProperties.getPartner());
            //生成随机字符串
            params.put("nonce_str", WXPayUtil.generateNonceStr());
            //商品描述
            params.put("body", order.getCourseTitle());
            //订单号
            params.put("out_trade_no", orderNo);
            //注意，这里必须使用字符串类型的参数（总金额：单位是分）
            String totalFee = order.getTotalFee().intValue() + "";
            params.put("total_fee", totalFee);
            //终端ip
            params.put("spbill_create_ip", remoteAddr);
            //通知地址(回调地址)
            params.put("notify_url", weixinPayProperties.getNotifyUrl());
            //交易类型
            params.put("trade_type", "NATIVE");

            //将参数转换成xml字符串格式：生成带有签名的xml格式字符串
            String xmlParams = WXPayUtil.generateSignedXml(params, weixinPayProperties.getPartnerKey());
            log.info("\n xmlParams：\n" + xmlParams);

            //将参数放入请求对象的方法体
            client.setXmlParam(xmlParams);
            //使用https形式发送
            client.setHttps(true);
            //发送请求
            client.post();
            //得到响应结果
            String resultXml = client.getContent();
            log.info("\n resultXml：\n" + resultXml);
            //将xml响应结果转成map对象
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultXml);

            //错误处理
            if("FAIL".equals(resultMap.get("return_code")) || "FAIL".equals(resultMap.get("result_code"))){
                log.error("微信支付统一下单错误 - "
                        + "return_code: " + resultMap.get("return_code")
                        + "return_msg: " + resultMap.get("return_msg")
                        + "result_code: " + resultMap.get("result_code")
                        + "err_code: " + resultMap.get("err_code")
                        + "err_code_des: " + resultMap.get("err_code_des"));

                throw new ColeExcepiton(ResultCodeEnum.PAY_UNIFIEDORDER_ERROR);
            }

            //组装需要的内容
            Map<String, Object> map = new HashMap<>();
            //响应码
            map.put("result_code", resultMap.get("result_code"));
            //生成二维码的url
            map.put("code_url", resultMap.get("code_url"));
            //课程id
            map.put("course_id", order.getCourseId());
            //订单总金额
            map.put("total_fee", order.getTotalFee());
            //订单号
            map.put("out_trade_no", orderNo);

            return map;

        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new ColeExcepiton(ResultCodeEnum.PAY_UNIFIEDORDER_ERROR);
        }
    }
}