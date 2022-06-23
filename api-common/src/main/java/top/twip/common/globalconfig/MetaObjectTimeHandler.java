package top.twip.common.globalconfig;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @Author: 七画一只妖
 * @Date: 2022-06-22 10:05
 */
@Component
public class MetaObjectTimeHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        fillStrategy(metaObject, "ctime", System.currentTimeMillis());
        fillStrategy(metaObject, "mtime", System.currentTimeMillis());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        fillStrategy(metaObject, "mtime", System.currentTimeMillis());
    }


}
