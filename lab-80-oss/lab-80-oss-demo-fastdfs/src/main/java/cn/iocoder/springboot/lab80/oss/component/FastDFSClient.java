package cn.iocoder.springboot.lab80.oss.component;

import cn.iocoder.springboot.lab80.oss.entity.ImageUrlObj;
import com.github.tobato.fastdfs.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

/**
 * FastDFSClient 类
 *
 * @author jaquez
 * @date 2021/12/09 16:51
 **/
@Component
public class FastDFSClient<T> {

    private static Logger log = LoggerFactory.getLogger(FastDFSClient.class);

    private static FastFileStorageClient fastFileStorageClient;

    private static FdfsWebServer fdfsWebServer;

    // 缩略图
    private static ThumbImageConfig ThumbImageConfig;

    @Autowired
    public void setFastDFSClient(FastFileStorageClient fastFileStorageClient, FdfsWebServer fdfsWebServer,ThumbImageConfig thumbImageConfig) {
        FastDFSClient.fastFileStorageClient = fastFileStorageClient;
        FastDFSClient.fdfsWebServer = fdfsWebServer;
        FastDFSClient.ThumbImageConfig = thumbImageConfig;
    }

    /**
     * @param multipartFile 文件对象
     * @return 返回文件地址
     * @author jaquez
     * @description 上传文件
     */
    public static String uploadFile(MultipartFile multipartFile) {
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()), null);
            // 返回参数带组信息，如：/group1/M00/00/00/wKixBGGxzfWACUqmAAIPcVAqdGw36.jpeg
            return storePath.getFullPath();
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * @param multipartFile 图片对象
     * @return 返回图片地址
     * @author jaquez
     * @description 上传缩略图
     */
    public static <T> T uploadImageAndCrtThumbImage(MultipartFile multipartFile, Class<T> t) {
        try {
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(), multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()), null);
            String thumbImagePath = ThumbImageConfig.getThumbImagePath(storePath.getFullPath());
            if(t.equals(String.class)){
                return (T) storePath.getFullPath();
            }
            if(t.equals(ImageUrlObj.class)){
                ImageUrlObj imageUrlObj = new ImageUrlObj();
                imageUrlObj.setImagePath(storePath.getFullPath());
                imageUrlObj.setThumbImagePath(thumbImagePath);
                return (T) imageUrlObj;
            }else{
                log.error("Class t is only String or ImageUrlObj!");
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * @param file 文件对象
     * @return 返回文件地址
     * @author jaquez
     * @description 上传文件
     */
    public static String uploadFile(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), null);
            return storePath.getFullPath();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * @param file 图片对象
     * @return 返回图片JSON字符串
     * @author jaquez
     * @description 上传缩略图
     */
    public static <T> T uploadImageAndCrtThumbImage(File file, Class<T> t) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), null);
            String thumbImagePath = ThumbImageConfig.getThumbImagePath(storePath.getFullPath());
            if(t.equals(String.class)){
                return (T) storePath.getFullPath();
            }
            if(t.equals(ImageUrlObj.class)){
                ImageUrlObj imageUrlObj = new ImageUrlObj();
                imageUrlObj.setImagePath(storePath.getFullPath());
                imageUrlObj.setThumbImagePath(thumbImagePath);
                return (T) imageUrlObj;
            }else{
                log.error("Class t is only String or ImageUrlObj!");
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * @param bytes byte数组
     * @param fileExtension 文件扩展名
     * @return 返回文件地址
     * @author jaquez
     * @description 将byte数组生成一个文件上传
     */
    public static String uploadFile(byte[] bytes, String fileExtension) {
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        StorePath storePath = fastFileStorageClient.uploadFile(stream, bytes.length, fileExtension, null);
        return storePath.getFullPath();
    }

    /**
     * @param fileUrl 文件访问地址
     * @param file 文件保存路径
     * @author jaquez
     * @description 下载文件
     */
    public static boolean downloadFile(String fileUrl, File file) {
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            byte[] bytes = fastFileStorageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(bytes);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * @param fileUrl 文件访问地址
     * @author jaquez
     * @description 删除文件
     */
    public static boolean deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return false;
        }
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    // 封装文件完整URL地址
    public static String getResAccessUrl(String path) {
        String url = fdfsWebServer.getWebServerUrl() + path;
        log.info("上传文件地址为：\n" + url);
        return url;
    }

}
