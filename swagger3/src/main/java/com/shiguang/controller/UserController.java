package com.shiguang.controller;

import com.shiguang.pojo.User;
import com.shiguang.utils.MD5Util;
import com.shiguang.utils.Result;
import io.swagger.annotations.*;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Shiguang On 2024/9/13 10:12
 */
@RestController
@RequestMapping("/user")

@ApiModel(value = "用户信息", description = "用户信息的模型")
@Api(value = "用户API", description = "用户相关的API")
public class UserController {

    // http://127.0.0.1:8080/user/{userId}
    @ApiOperation(value = "获取用户信息", notes = "根据用户ID获取用户信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功", response = User.class),
            @ApiResponse(code = 404, message = "用户不存在")
    })
    @GetMapping("/{userId}")
    public Result<User> getUserById(@ApiParam(value = "用户ID", required = true)
                                        @PathVariable(name = "userId") Long userId) {


        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
        user.setUserId(userId);
        user.setEmail("zhangsan@shiguang.com");
        user.setPassword(MD5Util.encrypt("123456"));

        return Result.ok(user);


    }
}
