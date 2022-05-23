package top.twip.cherry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.twip.cherry.service.ProductService;
import top.twip.common.response.DataFactory;
import top.twip.common.response.SimpleData;

import javax.annotation.Resource;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-20 19:14
 */
@RestController
@RequestMapping("/cherry")
public class CherryController {

    @Resource
    private ProductService productService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello cherry";
    }

    @GetMapping("/getAllProduct")
    public Object getAllProduct() {
        return DataFactory.success(SimpleData.class, "查询成功").parseData(productService.getAllProduct());
    }
}
