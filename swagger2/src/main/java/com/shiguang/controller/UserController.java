package com.shiguang.controller;

import com.shiguang.pojo.User;
import com.shiguang.utils.MD5Util;
import com.shiguang.utils.PinyinConverter;
import com.shiguang.utils.Result;
import com.shiguang.utils.ResultCodeEnum;
import io.swagger.annotations.*;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created By Shiguang On 2024/9/13 10:12
 */
@RestController
@RequestMapping("user")
@ApiModel(value = "用户信息", description = "用户信息的模型")
@Api(tags = "用户管理", description = "用户相关的API")
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
        System.out.println("getUserById params:" + userId);

        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
        user.setUserId(userId);
        user.setEmail("zhangsan@shiguang.com");
        user.setPassword(MD5Util.encrypt("123456"));
        return Result.ok(user);

    }

    @ApiOperation(value = "新增用户信息", notes = "新增一个用户信息")
    @PostMapping()
    public Result<User> addUser(@RequestBody User user) {
        System.out.println("addUser params:" + user);
        if (user == null || StringUtils.isBlank(user.getUsername())) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        if (StringUtils.isBlank(user.getPassword())) {
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }

        user.setUserId(System.currentTimeMillis());
        String pinyin = PinyinConverter.toPinyin(user.getUsername());
        String email = pinyin + "@shiguang.com";
        user.setEmail(email);


        user.setPassword(MD5Util.encrypt(user.getPassword()));

        return Result.ok(user);
    }

    @ApiOperation(value = "修改用户信息", notes = "修改一个用户信息")
    @PutMapping()
    public Result<User> updateUser(@RequestBody User user) {
        System.out.println("updateUser params:" + user);
        if (user == null || StringUtils.isBlank(user.getUsername()) || user.getUserId() == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        String pinyin = PinyinConverter.toPinyin(user.getUsername());

        if (StringUtils.isBlank(user.getEmail())) {
            String email = pinyin + "@shiguang.com";
            user.setEmail(email);
        }

        if (StringUtils.isBlank(user.getPassword())) {
             user.setPassword(MD5Util.encrypt(pinyin));
        }

        user.setPassword(MD5Util.encrypt(user.getPassword()));




        return Result.ok(user);
    }

    @ApiOperation(value = "删除用户信息", notes = "删除一个用户信息")
    @DeleteMapping("/{userId}")
    public Result<Boolean> deleteUser(@ApiParam(value = "用户ID", required = true)
                                      @PathVariable(name = "userId") Long userId) {
        System.out.println("deleteUser params:" + userId);
        return Result.ok(true);
    }


}
