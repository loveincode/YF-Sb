package com.loveincode.enums;

/**
 * @author huyifan
 * @date :2019-05-28
 * com.loveincode.enums
 */
public enum StatusEnums {
    /**
     * status enums
     */
    UNPAID("UNPAID", "待支付"),
    WAITING_FOR_RECEIVE("WAITING_FOR_RECEIVE", "待收货"),
    DONE("DONE", "结束"),
    ;

    private String code;
    private String des;

    public String getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }

    StatusEnums(String code, String des) {
        this.code = code;
        this.des = des;
    }

}
