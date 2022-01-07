package cn.iocoder.springboot.lab83.demo.postprocessor;

/**
 * 演示类
 *
 * @author jaquez
 * @date 2022/01/07 11:56
 **/
public class Person4 {

    private Long id = 1L;

    private String name = "person";

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
        return "Person4{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
