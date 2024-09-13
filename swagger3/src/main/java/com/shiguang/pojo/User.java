package com.shiguang.pojo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created By Shiguang On 2024/9/13 10:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty(value = "用户邮箱")
    private String email;
    @ApiModelProperty(value = "用户ID",required = true)
    private Long userId;

}
