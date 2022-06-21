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
            try {
                return DataFactory.success(SimpleData.class, "查询成功").parseData(userService.getAllUserInfo());
            } catch (Exception e) {
                return DataFactory.fail(CodeEnum.INTERNAL_ERROR, "直接查询发生错误，可能是数据库异常");
            }
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
            try {
                return DataFactory.success(SimpleData.class, "查询成功").parseData(userService.getUserInfoById(id));
            } catch (Exception e) {
                return DataFactory.fail(CodeEnum.NOT_FOUND, e.getMessage());
            }
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
            try {
                return DataFactory.success(SimpleData.class, "添加成功").parseData(userService.addUserInfo(userInfo));
            } catch (Exception e) {
                return DataFactory.fail(CodeEnum.NOT_ALL_OK, e.getMessage());
            }
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
            try {
                return DataFactory.success(SimpleData.class, "修改成功").parseData(userService.updateUserInfo(userInfo));
            } catch (Exception e) {
                return DataFactory.fail(CodeEnum.INTERNAL_ERROR, e.getMessage());
            }
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
        try{
            userService.deleteUserInfo(id);
            return DataFactory.success(SimpleData.class, "删除成功");
        }catch (Exception e){
            return DataFactory.fail(CodeEnum.INTERNAL_ERROR, e.getMessage());
        }
    }
}
