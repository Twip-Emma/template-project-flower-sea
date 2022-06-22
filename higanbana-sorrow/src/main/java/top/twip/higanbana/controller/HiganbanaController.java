package top.twip.higanbana.controller;

import org.springframework.web.bind.annotation.*;
import top.twip.common.constant.FeignConstants;
import top.twip.common.entity.user.UserInfo;
import top.twip.common.enums.CodeEnum;
import top.twip.common.response.DataFactory;
import top.twip.common.response.SimpleData;
import top.twip.higanbana.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-20 18:53
 */
@RestController
@RequestMapping("/higanbana")
public class HiganbanaController {

    @Resource
    private UserService userService;

    /**
     * 获取全部用户
     */
    @RequestMapping("/getAllUser")
    public Object index(HttpServletRequest request) throws Exception {
        if(request.getHeader(FeignConstants.HEADER_NAME.getValue()) != null) {
            return userService.getAllUserInfo();
        }
        else {
            return DataFactory.success(SimpleData.class, "查询成功").parseData(userService.getAllUserInfo());
        }
    }

    /**
     * 根据用户id查询用户
     * @param id 用户id
     */
    @PostMapping("/getUserById")
    public Object getUserById(@RequestParam String id,
                              HttpServletRequest request) throws Exception {

        if(request.getHeader(FeignConstants.HEADER_NAME.getValue()) != null) {
            return userService.getUserInfoById(id);
        } else {
            return DataFactory.success(SimpleData.class, "查询成功").parseData(userService.getUserInfoById(id));
        }
    }

    /**
     * 根据用户名查询用户
     * @param userInfo 用户信息
     * @return 用户信息
     */
    @PostMapping("/addUser")
    public Object addUser(@RequestBody UserInfo userInfo,
                          HttpServletRequest request) throws Exception {
        if (request.getHeader(FeignConstants.HEADER_NAME.getValue()) != null) {
            return userService.addUserInfo(userInfo);
        }else {
            return DataFactory.success(SimpleData.class, "添加成功").parseData(userService.addUserInfo(userInfo));
        }
    }

    /**
     * 修改用户
     * @param userInfo 用户信息
     */
    //todo 未测试
    @PostMapping("/updateUser")
    public Object updateUser(@RequestBody UserInfo userInfo,
                             HttpServletRequest request) throws Exception {
        if (request.getHeader(FeignConstants.HEADER_NAME.getValue()) != null) {
            return userService.updateUserInfo(userInfo);
        }else {
            return DataFactory.success(SimpleData.class, "修改成功").parseData(userService.updateUserInfo(userInfo));
        }
    }


    /**
     * 删除用户
     * @param id 用户id
     */
    //todo 未测试
    @PostMapping("/deleteUser")
    public Object deleteUser(@RequestParam String id,
                             HttpServletRequest request) throws Exception {
        userService.deleteUserInfo(id);
        return DataFactory.success(SimpleData.class, "删除成功");
    }
}
