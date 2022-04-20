package top.twip.common.response;


import lombok.Getter;
import top.twip.common.enums.CodeEnum;

/**
 * @author : 陈征
 * @date : 2022-01-10 18:19
 */

@Getter
public abstract class BaseData {
    protected int code = CodeEnum.OK.value();

    protected String msg;

    protected abstract void parse(Object entity);

    public BaseData parseData(Object entity) {
        parse(entity);
        return this;
    }

    public BaseData parseData(Object... objects) {
        for (Object object : objects) {
            parse(object);
        }
        return this;
    }
}
