package top.twip.common.enums;

/**
 * @author : 七画一只妖
 * @date : 2022-02-14 17:29
 */

public enum CodeEnum {
    INTERNAL_ERROR(500), // 服务器内部错误
    OK(200), // 成功
    NOT_FOUND(404), // 找不到请求的资源
    FORBIDDEN(403), // 请求被拒绝
    UNAUTHORIZED(401), // 未授权不通过
    BAD_REQUEST(400), // 服务器无法解析该请求
    TOKEN_EXPIRED(1001); // 自定义：TOKEN过期

    private final Integer code;

    CodeEnum(int code) {
        this.code = code;
    }

    public int value() {
        return code;
    }
}
