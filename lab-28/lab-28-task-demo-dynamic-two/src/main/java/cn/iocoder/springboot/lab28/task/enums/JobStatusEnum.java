package cn.iocoder.springboot.lab28.task.enums;

/**
 * 操作Job枚举类
 * @author Jaquez
 * @date 2021/07/08 15:44
 */
public enum JobStatusEnum {
    /**
     * 0=暂停
     */
    STOP(0, "暂停"),
    /**
     * 1=正常
     */
    NORAML(1, "正常");

    private String value = null;
    private Integer code = null;

    private JobStatusEnum(Integer _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    /**
     * 通过Key获取
     * @param key
     * @return
     */
    public static JobStatusEnum getEnumByKey(Integer key) {
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
    public Integer getCode() {
        return code;
    }

}
