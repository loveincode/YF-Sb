package com.loveincode.enums;

/**
 * @author huyifan
 * @date :2019-05-28
 * com.loveincode.enums
 */
public enum EventsEnums {
    /**
     * Events enums
     */
    PAY("PAY", "支付"),
    RECEIVE("RECEIVE", "收货"),
    ;

    private String code;
    private String des;

    public String getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }

    EventsEnums(String code, String des) {
        this.code = code;
        this.des = des;
    }
}
