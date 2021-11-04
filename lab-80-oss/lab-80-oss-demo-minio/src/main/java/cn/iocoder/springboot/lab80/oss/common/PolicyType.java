package cn.iocoder.springboot.lab80.oss.common;

/**
 * 策略类型
 *
 * @author jaquez
 * @date 2021/11/03 18:49
 **/
public enum PolicyType {
    NONE("none"), READ_ONLY("readonly"), READ_WRITE("readwrite"), WRITE_ONLY("writeonly");
    private final String value;


    private PolicyType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

