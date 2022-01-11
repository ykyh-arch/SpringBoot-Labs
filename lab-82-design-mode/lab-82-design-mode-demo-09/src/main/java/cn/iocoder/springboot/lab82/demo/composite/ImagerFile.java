package cn.iocoder.springboot.lab82.demo.composite;

/**
 * 图片类
 *
 * @author jaquez
 * @date 2022/01/11 11:22
 **/
public class ImagerFile extends File{

    public ImagerFile(String name) {
        super(name);
    }

    public void display() {
        System.out.println("这是图像文件，文件名：" + super.getName());
    }

}
