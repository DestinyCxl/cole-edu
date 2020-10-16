package com.cole.service.trade.service.impl;

import com.cole.service.trade.entity.PayLog;
import com.cole.service.trade.mapper.PayLogMapper;
import com.cole.service.trade.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author Cxl
 * @since 2020-10-10
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
