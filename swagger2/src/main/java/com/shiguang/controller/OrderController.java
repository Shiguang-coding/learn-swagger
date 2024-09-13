package com.shiguang.controller;

import com.shiguang.pojo.Order;
import com.shiguang.utils.Result;
import com.shiguang.utils.ResultCodeEnum;
import io.swagger.annotations.*;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

/**
 * Created By Shiguang On 2024/9/13 14:03
 */
@RestController
@RequestMapping("order")
@ApiModel(value = "订单信息", description = "订单的模型")
@Api(tags = "订单管理", description = "订单相关的API")
public class OrderController {


    // http://127.0.0.1:8080/order/{orderId}
    @ApiOperation(value = "获取订单信息", notes = "根据订单ID获取订单信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功", response = Order.class),
            @ApiResponse(code = 404, message = "订单不存在")
    })
    @GetMapping("/{orderId}")
    public Result<Order> getOrderById(@ApiParam(value = "订单ID", required = true)
                                      @PathVariable(name = "orderId") Long orderId) {
        System.out.println("getOrderById params:" + orderId);
        Order order = new Order();

        order.setOrderId(orderId);
        order.setUserId(new Random().nextLong(10001));
        order.setOrderName("商品" + orderId);
        order.setOrderPrice(new Random().nextDouble(101));
        order.setOrderTime(new Date());

        return Result.ok(order);

    }

    @ApiOperation(value = "新增订单信息", notes = "新增一个订单信息")
    @PostMapping()
    public Result<Order> addOrder(@RequestBody Order order) {
        System.out.println("addOrder params:" + order);
        if (order == null || StringUtils.isBlank(order.getOrderName())
               || order.getUserId() == null || order.getOrderPrice() == null) {
            return Result.build(null, ResultCodeEnum.ORDER_ERR);
        }

        order.setOrderId(System.currentTimeMillis());
        order.setOrderTime(new Date());

        return Result.ok(order);
    }

    @ApiOperation(value = "修改订单信息", notes = "修改一个订单信息")
    @PutMapping()
    public Result<Order> updateOrder(@RequestBody Order order) {
        System.out.println("updateOrder params:" + order);
        if (order == null || order.getOrderId() == null || order.getOrderPrice() == null) {
            return Result.build(null, ResultCodeEnum.ORDER_ERR);
        }

        if (order.getUserId() == null){
            order.setUserId(new Random().nextLong(800));
        }
        order.setOrderTime(new Date());
        if (StringUtils.isBlank(order.getOrderName())){
            order.setOrderName("商品" + order.getOrderId());
        }


        return Result.ok(order);
    }

    @ApiOperation(value = "删除订单信息", notes = "删除一个订单信息")
    @DeleteMapping("/{orderId}")
    public Result<Boolean> deleteOrder(@ApiParam(value = "订单ID", required = true)
                                       @PathVariable(name = "orderId") Long orderId) {
        System.out.println("deleteOrder params:" + orderId);
        return Result.ok(true);
    }
}
