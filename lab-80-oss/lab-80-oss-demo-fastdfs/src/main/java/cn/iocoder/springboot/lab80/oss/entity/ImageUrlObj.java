package cn.iocoder.springboot.lab80.oss.entity;

/**
 * 图片路径对象
 *
 * @author jaquez
 * @date 2021/12/09 18:37
 **/
public class ImageUrlObj {

    private String imagePath;

    private String thumbImagePath;


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getThumbImagePath() {
        return thumbImagePath;
    }

    public void setThumbImagePath(String thumbImagePath) {
        this.thumbImagePath = thumbImagePath;
    }

    @Override
    public String toString() {
        return "ImageUrlObj{" +
                "imagePath='" + imagePath + '\'' +
                ", thumbImagePath='" + thumbImagePath + '\'' +
                '}';
    }
}
