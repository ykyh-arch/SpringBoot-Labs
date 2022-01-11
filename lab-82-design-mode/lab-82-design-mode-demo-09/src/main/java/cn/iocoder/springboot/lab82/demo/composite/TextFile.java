package cn.iocoder.springboot.lab82.demo.composite;

/**
 * 文本类
 *
 * @author jaquez
 * @date 2022/01/11 11:21
 **/
public class TextFile extends File{

    public TextFile(String name) {
        super(name);
    }

    // 浏览方法
    public void display() {
        System.out.println("这是文本文件，文件名：" + super.getName());
    }

}
