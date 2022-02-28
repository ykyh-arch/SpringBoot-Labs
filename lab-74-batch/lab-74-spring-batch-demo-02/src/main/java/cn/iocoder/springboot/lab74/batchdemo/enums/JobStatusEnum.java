package cn.iocoder.springboot.lab74.batchdemo.enums;

/**
 * 操作 Job 枚举类
 *
 * @author Jaquez
 * @date 2021/07/08 15:44
 */
public enum JobStatusEnum {
    /**
     * 0-停止
     */
    STOP("0", "停止"),
    /**
     * 1-运行
     */
    RUNNING("1", "运行");

    private String value = null;
    private String code = null;

    private JobStatusEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    /**
     * 通过 Key 获取
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

    /** 获取 value */
    public String getValue() {
        return value;
    }

    /** 获取 code */
    public String getCode() {
        return code;
    }

}
