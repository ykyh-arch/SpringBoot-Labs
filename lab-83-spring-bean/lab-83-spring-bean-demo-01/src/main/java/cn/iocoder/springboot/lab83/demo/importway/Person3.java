package cn.iocoder.springboot.lab83.demo.importway;

/**
 * 演示类
 *
 * @author jaquez
 * @date 2022/01/07 11:56
 **/
public class Person3 {

    private Long id = 1L;

    private String name = "person3";

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
        return "Person3{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
