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
}
