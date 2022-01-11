package cn.iocoder.springboot.lab82.demo.composite;

/**
 * 文件类
 *
 * @author jaquez
 * @date 2022/01/11 11:17
 **/
public abstract class File {

    String name;

    public File(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 浏览方法
    public abstract void display();

}
