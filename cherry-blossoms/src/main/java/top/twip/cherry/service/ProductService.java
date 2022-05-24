package top.twip.cherry.service;

import org.springframework.stereotype.Component;
import top.twip.cherry.dao.ProductInfoDao;
import top.twip.common.entity.product.ProductInfo;
import top.twip.common.entity.user.UserInfo;
import top.twip.common.feign.HiganbanaClient;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 七画一只妖
 * @Date: 2022-05-23 20:59
 */
@Component
public class ProductService {

    @Resource
    private ProductInfoDao productInfoDao;

    @Resource
    private HiganbanaClient higanbanaClient;

    public Object getAllProduct() {
        List<ProductInfo> productInfos = productInfoDao.selectList(null);
        for (ProductInfo productInfo : productInfos) {
            UserInfo userInfo = higanbanaClient.getUserById(productInfo.getUserId());
            productInfo.setUserInfo(userInfo);
        }
        return productInfos;
    }
}
