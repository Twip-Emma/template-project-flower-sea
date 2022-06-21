package top.twip.higanbana.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.twip.common.entity.user.UserInfo;
import top.twip.common.exception.DatabaseDataNotFound;
import top.twip.common.exception.DatabaseHandlerException;
import top.twip.higanbana.dao.UserInfoDao;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 七画一只妖
 * @Date: 2022-04-20 21:42
 */
@Service
public class UserService{

    @Resource
    private UserInfoDao userInfoDao;

    // 获取全部用户信息
    public List<UserInfo> getAllUserInfo() {
        return userInfoDao.selectList(null);
    }

    /**
     * 根据ID获取用户信息
     */
    public UserInfo getUserInfoById(String id) throws Exception{
        UserInfo userInfo = userInfoDao.selectById(id);
        if (userInfo == null) {
            throw new DatabaseDataNotFound("数据未找到");
        }else {
            return userInfo;
        }
    }

    /**
     * 新增用户
     */
    public String addUserInfo(UserInfo userInfo) throws Exception{
        String userMail = userInfo.getUserMail();
        // 判断user_mail是否已存在
        List<UserInfo> userInfo1 = userInfoDao.selectList(new QueryWrapper<UserInfo>()
                .eq("user_mail", userMail));
        if (userInfo1.size() != 0) {
            throw new DatabaseHandlerException("用户邮箱已存在");
        }

        // 执行插入操作
        int insert = userInfoDao.insert(userInfo);
        if (insert == 0) {
            throw new DatabaseHandlerException("数据库操作失败");
        }

        // 查询这个用户的ID
        UserInfo reUserEntity = userInfoDao.selectOne(new QueryWrapper<>(userInfo));
        if (reUserEntity == null) {
            throw new DatabaseHandlerException("数据库操作失败");
        }else{
            return reUserEntity.getUserId();
        }
    }
}
