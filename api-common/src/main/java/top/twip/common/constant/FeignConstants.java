package top.twip.common.constant;

/**
 * @Author: 七画一只妖
 * @Date: 2022-05-24 16:32
 */
public enum FeignConstants {
    HEADER_NAME("Authorization"),
    HEADER_VALUE("Feign");

    private final String value;

    FeignConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
