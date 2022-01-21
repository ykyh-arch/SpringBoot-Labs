package cn.iocoder.springboot.lab28.task.enums;

/**
 * 操作Job枚举类
 *
 * @author Jaquez
 * @date 2021/07/08 15:44
 */
public enum JobStatusEnum {
    /**
     * 0=停止
     */
    STOP("0", "禁用"),
    /**
     * 1=运行
     */
    RUNNING("1", "启用");

    private String value = null;
    private String code = null;

    private JobStatusEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    /**
     * 通过Key获取
     * @param key
     * @return
     */
    public static JobStatusEnum getEnumByKey(String key) {
        for (JobStatusEnum e : JobStatusEnum.values()) {
            if (e.getCode().equals(key)) {
                return e;
            }
        }
        return null;
    }

    /** 获取value */
    public String getValue() {
        return value;
    }

    /** 获取code */
    public String getCode() {
        return code;
    }

}
