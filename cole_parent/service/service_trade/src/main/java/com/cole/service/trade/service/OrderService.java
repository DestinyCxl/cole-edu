package com.cole.service.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cole.service.trade.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author Cxl
 * @since 2020-10-10
 */
public interface OrderService extends IService<Order> {

    String saveOrder(String courseId, String id);

    Order getByOrderId(String orderId, String memberId);

    Boolean isBuyByCourseId(String courseId,  String memberId);

    List<Order> selectByMemberId(String memberId);

    boolean removeById(String orderId, String memberId);

    Order getOrderByOrderNo(String orderNo);

    void updateOrderStatus(Map<String, String> map);

    boolean queryPayStatus(String orderNo);
}
