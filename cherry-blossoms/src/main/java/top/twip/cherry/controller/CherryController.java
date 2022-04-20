package top.twip.cherry.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-20 19:14
 */
@RestController
@RequestMapping("/cherry")
public class CherryController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello cherry";
    }
}
