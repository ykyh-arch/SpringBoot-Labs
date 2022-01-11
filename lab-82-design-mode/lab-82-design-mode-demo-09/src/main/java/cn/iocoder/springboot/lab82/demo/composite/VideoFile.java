package cn.iocoder.springboot.lab82.demo.composite;

/**
 * 视频类
 *
 * @author jaquez
 * @date 2022/01/11 11:23
 **/
public class VideoFile extends File{

    public VideoFile(String name) {
        super(name);
    }

    public void display() {
        System.out.println("这是影像文件，文件名：" + super.getName());
    }

}
