package cn.iocoder.springboot.lab12.mybatis.entity;

/**
 * 加密、解密类，此实体类的数据都表示需要加解密的
 *
 * @author jaquez
 * @date 2022/01/04 16:03
 **/
public class Encrypt {

    private String value;

    public Encrypt() {
    }

    public Encrypt(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
