package top.twip.higanbana.controller;

import org.springframework.web.bind.annotation.*;
import top.twip.common.response.DataFactory;
import top.twip.common.response.SimpleData;
import top.twip.higanbana.service.UserService;

import javax.annotation.Resource;

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
    public Object index() {
        return DataFactory.success(SimpleData.class, "查询成功").parseData(userService.getAllUserInfo());
    }

    @PostMapping("/getUserById")
    public Object getUserById(@RequestParam String id) {
        return DataFactory.success(SimpleData.class, "查询成功").parseData(userService.getUserInfoById(id));
    }
}
