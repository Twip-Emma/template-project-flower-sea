package top.twip.higanbana.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.twip.common.entity.user.UserInfo;
import top.twip.higanbana.dao.UserInfoDao;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-20 21:42
 */
@Service
public class UserService {

    @Resource
    private UserInfoDao userInfoDao;

    // 获取全部用户信息
    public List<UserInfo> getAllUserInfo() {
        return userInfoDao.selectList(null);
    }

    /**
     * 根据ID获取用户信息
     */
    public UserInfo getUserInfoById(String id) {
//        return userInfoDao.selectOne(new QueryWrapper<UserInfo>()
//                .eq("user_id", id));
        return userInfoDao.selectById(id);
    }
}
