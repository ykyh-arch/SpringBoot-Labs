package cn.iocoder.springboot.lab80.oss.service;

import cn.iocoder.springboot.lab80.oss.component.FastDFSClient;
import cn.iocoder.springboot.lab80.oss.entity.ImageUrlObj;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * FileClientService 类
 *
 * @author jaquez
 * @date 2021/12/10 10:44
 **/
@Service
public class FileClientService<T> {

    /**
     * 上传文件
     * @author Jaquez
     * @date 2021/12/10 10:48
     * @param multipartFile
     * @return java.lang.String
     */
    public String upload(MultipartFile multipartFile) {
        String returnUrlPath = FastDFSClient.uploadFile(multipartFile);
        return returnUrlPath;
    }

    /**
     * 获取完整的访问URL
     * @author Jaquez
     * @date 2021/12/10 11:51
     * @param url
     * @return java.lang.String
     */
    public String getResAccessUrl(String url){
        return FastDFSClient.getResAccessUrl(url);
    }

    /**
     * 下载文件
     * @author Jaquez
     * @date 2021/12/10 14:04
     * @param fileUrl
     * @return void
     */
    public void download(String fileUrl, HttpServletResponse response) throws IOException {
        FastDFSClient.downloadFile(fileUrl,response);
    }

    /**
     * 删除文件
     * @author Jaquez
     * @date 2021/12/10 15:56
     * @param fileUrl
     * @return java.lang.Boolean
     */
    public Boolean delete(String fileUrl){
        Boolean b = FastDFSClient.deleteFile(fileUrl);
        return b;
    }

    /**
     * 上传图片
     * @author Jaquez
     * @date 2021/12/10 16:21
     * @param multipartFile
     * @param isShowThumbImage
     * @return T
     */
    public <T> T uploadImage(MultipartFile multipartFile, Integer isShowThumbImage) {
        if(isShowThumbImage ==0){
            return (T)FastDFSClient.<String>uploadImageAndCrtThumbImage(multipartFile,String.class);
        }
        if(isShowThumbImage ==1){
            return (T)FastDFSClient.<ImageUrlObj>uploadImageAndCrtThumbImage(multipartFile, ImageUrlObj.class);
        }
        return null;
    }
}
