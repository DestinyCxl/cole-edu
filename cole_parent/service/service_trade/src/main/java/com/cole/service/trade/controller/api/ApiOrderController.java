package com.cole.service.trade.controller.api;

import com.cole.common.base.result.R;
import com.cole.common.base.result.ResultCodeEnum;
import com.cole.common.base.util.JwtInfo;
import com.cole.common.base.util.JwtUtils;
import com.cole.service.trade.entity.Order;
import com.cole.service.trade.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Cxl
 * @since: 2020-10-11
 **/
@RestController
@RequestMapping("/api/trade/order")
@Api(description = "网站订单管理")
//@CrossOrigin //跨域
@Slf4j
public class ApiOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1.新增订单
     * @param courseId
     * @param request
     * @return
     */
    @ApiOperation("新增订单")
    @PostMapping("/auth/save/{courseId}")
    public R save(@PathVariable String courseId, HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        String orderId = orderService.saveOrder(courseId, jwtInfo.getId());
        return R.ok().data("orderId", orderId);
    }

    /**
     * 2.在订单支付页面获取订单信息
     * @param orderId
     * @param request
     * @return
     */
    @ApiOperation("获取订单")
    @GetMapping("/auth/get/{orderId}")
    public R get(@PathVariable String orderId, HttpServletRequest request) {
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        Order order = orderService.getByOrderId(orderId, jwtInfo.getId());
        return R.ok().data("item", order);
    }

    /**
     * 3.判断课程是否已购买
     * @param courseId
     * @param request
     * @return
     */
    @ApiOperation( "判断课程是否购买")
    @GetMapping("/auth/is-buy/{courseId}")
    public R isBuyByCourseId(@PathVariable String courseId, HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        Boolean isBuy = orderService.isBuyByCourseId(courseId, jwtInfo.getId());
        return R.ok().data("isBuy", isBuy);
    }

    /**
     * 4.在个人用户页面获取当前用户订单列表
     * @param request
     * @return
     */
    @ApiOperation(value = "获取当前用户订单列表")
    @GetMapping("/auth/list")
    public R list(HttpServletRequest request) {
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        List<Order> list = orderService.selectByMemberId(jwtInfo.getId());
        return R.ok().data("items", list);
    }

    /**
     * 5.删除该用户下的单条订单信息
     * @param orderId
     * @param request
     * @return
     */
    @ApiOperation(value = "删除订单")
    @DeleteMapping("/auth/remove/{orderId}")
    public R remove(@PathVariable String orderId, HttpServletRequest request) {
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        boolean result = orderService.removeById(orderId, jwtInfo.getId());
        if (result) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    /**
     * 6.前端在展示支付二维码后对后端不断进行轮询订单支付结果
     * @param orderNo
     * @return
     */
    @ApiOperation(value = "前端查询订单结果")
    @GetMapping("/query-pay-status/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        boolean result = orderService.queryPayStatus(orderNo);
        if (result) {//支付成功
            return R.ok().message("支付成功");
        }
        return R.setResult(ResultCodeEnum.PAY_RUN);//支付中
    }



}