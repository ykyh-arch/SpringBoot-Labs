package cn.iocoder.springboot.lab12.mybatis.dataobject;

import cn.iocoder.springboot.lab12.mybatis.datamask.DataMasking;
import cn.iocoder.springboot.lab12.mybatis.datamask.DataMaskingFunc;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户 DO
 */
@TableName(value = "users")
public class UserDO implements Serializable {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 账号
     */
    @DataMasking(maskFunc = DataMaskingFunc.PART_MASK)
    private String username;
    /**
     * 密码（明文）
     *
     * ps：生产环境下，千万不要明文噢
     */
    @DataMasking(maskFunc = DataMaskingFunc.ALL_MASK)
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否删除，逻辑删除
     */
    @TableLogic
    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public UserDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public UserDO setDeleted(Integer deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", deleted=" + deleted +
                '}';
    }
}
