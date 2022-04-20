package top.twip.higanbana.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-20 18:53
 */
@RestController
@RequestMapping("/higanbana")
public class HiganbanaController {
    @RequestMapping("/index")
    public String index() {
        return "Higanbana";
    }
}
