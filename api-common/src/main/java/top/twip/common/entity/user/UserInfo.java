package top.twip.common.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-20 21:15
 */
@Data
@TableName("user_info")
public class UserInfo {
    private String userId;
    private String userName;
    private Integer userSex;
    private Integer userAge;
    private String userMail;
}
