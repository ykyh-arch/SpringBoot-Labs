package cn.iocoder.springboot.lab45.apollodemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用户类
 *
 * @author jaquez
 * @date 2021/09/18 11:57
 **/
@Entity
public class User {
    @Id
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }
}
