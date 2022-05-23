package top.twip.common.entity.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.twip.common.entity.user.UserInfo;

/**
 * @Author: 七画一只妖
 * @Date: 2022-05-23 20:50
 */
@Data
@TableName("product_info")
public class ProductInfo {
    private String productId;
    private String userId;
    private String productName;
    private String productPrice;
    private String productCount;
    private String productUrl;

    // 非数据库字段
    @TableField(exist = false)
    private UserInfo userInfo;
}
