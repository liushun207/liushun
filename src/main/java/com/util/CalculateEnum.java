package com.util;

/**
 * 计算枚举.
 */
public enum CalculateEnum {
    /**
     * 合
     */
    SUM(1, "合"),

    /**
     * 最小
     */
    MIN(2, "最小"),

    /**
     * 最大
     */
    MAX(3, "最大"),

    /**
     * 平均值
     */
    AVERAGE(4, "平均值"),
    ;

    // region 其它

    private int code;
    private String name;

    private CalculateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Gets code.
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    // endregion

}
