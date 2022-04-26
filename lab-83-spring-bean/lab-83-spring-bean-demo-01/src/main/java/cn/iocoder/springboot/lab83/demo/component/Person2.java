package cn.iocoder.springboot.lab83.demo.component;

import org.springframework.stereotype.Component;

/**
 * 方式二：@Component + @ComponentScan 方式
 *
 * @author jaquez
 * @date 2022/01/07 11:56
 **/
@Component
public class Person2 {

    private Long id = 1L;

    private String name = "person2";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
