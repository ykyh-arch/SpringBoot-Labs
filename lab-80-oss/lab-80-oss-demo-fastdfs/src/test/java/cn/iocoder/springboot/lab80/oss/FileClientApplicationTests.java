package cn.iocoder.springboot.lab80.oss;

import cn.iocoder.springboot.lab80.oss.component.FastDFSClient;
import cn.iocoder.springboot.lab80.oss.entity.ImageUrlObj;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * 测试类
 *
 * @author jaquez
 * @date 2021/12/09 17:10
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FileClientApplication.class)
public class FileClientApplicationTests {

    @Test
    public void Upload() {

        String fileUrl = this.getClass().getResource("/test.jpeg").getPath();
        File file = new File(fileUrl);
        String str = FastDFSClient.uploadFile(file);
        System.out.println("文件路径为："+FastDFSClient.getResAccessUrl(str));
    }

    @Test
    public void UploadImage1() {

        String fileUrl = this.getClass().getResource("/test.jpeg").getPath();
        File file = new File(fileUrl);
        String str = FastDFSClient.<String>uploadImageAndCrtThumbImage(file,String.class);
        System.out.println("结果为："+FastDFSClient.getResAccessUrl(str));
    }

    @Test
    public void UploadImage2() {

        String fileUrl = this.getClass().getResource("/test.jpeg").getPath();
        File file = new File(fileUrl);
        ImageUrlObj imageUrlObj = FastDFSClient.<ImageUrlObj>uploadImageAndCrtThumbImage(file, ImageUrlObj.class);
        System.out.println("图片为："+FastDFSClient.getResAccessUrl(imageUrlObj.getImagePath()));
        System.out.println("缩略图为："+FastDFSClient.getResAccessUrl(imageUrlObj.getThumbImagePath()));
    }

    @Test
    public void Delete() {
        FastDFSClient.deleteFile(
                "group1/M00/00/00/rBEAClu8OiSAFbN_AAbhXQnXzvw031.jpg");
    }


}

