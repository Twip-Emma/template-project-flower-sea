package top.twip.common.constant;

/**
 * @Author: 七画一只妖
 * @Date: 2022-06-23 9:18
 */
public enum CurrencyConstants {
    CURRENCY_HEADER_NAME("Currency"),
    CURRENCY_HEADER_VALUE("kkjb");

    private final String value;

    CurrencyConstants(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
