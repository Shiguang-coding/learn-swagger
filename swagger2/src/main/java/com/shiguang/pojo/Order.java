package com.shiguang.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created By Shiguang On 2024/9/13 14:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @ApiModelProperty(value = "订单ID")
    private Long orderId;
    @ApiModelProperty(value = "用户名")
    private Long userId;
    @ApiModelProperty(value = "订单名称")
    private String orderName;
    @ApiModelProperty(value = "订单价格")

    private Double orderPrice;
    @ApiModelProperty(value = "订单时间")
    private Date orderTime;
}
