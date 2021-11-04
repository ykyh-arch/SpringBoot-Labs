package cn.iocoder.springboot.lab80.oss.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * MinioService 服务层
 *
 * @author Jaquez
 * @date 2021/11/04 11:26
 */
public interface MinioService {

    /**
     * 获取文件列表
     *
     * @param bucketName 桶名称
     * @param prefix 对象前缀
     * @param recursive 是否递归
     * @return 对象列表
     * @throws Exception
     */
    public List<Object> listObjects(String bucketName,String prefix,Boolean recursive) throws Exception;

    /**
     * 上传操作
     *
     * @param endpoint 服务 Url
     * @param bucketName 桶名称
     * @param file 文件
     * @return
     * @throws Exception
     */
    public Object upload(String endpoint,String bucketName,MultipartFile[] file) throws Exception;

    /**
     * 下载文件
     *
     * @author Jaquez
     * @date 2021/11/04 14:02
     * @param response
     * @param bucketName 桶名称
     * @param fileName 文件名
     * @return void
     */
    public void download(HttpServletResponse response,String bucketName,String fileName);

    /**
     * 删除文件
     * @author Jaquez
     * @date 2021/11/04 14:17
     * @param bucketName 桶名称
     * @param fileName 文件名
     * @return java.lang.Integer
     */
    Integer delete(String bucketName,String fileName);

    /**
     * 获取可以预览的文件列表
     *
     * @param bucketName 桶名称
     * @param prefix 对象前缀
     * @param recursive 是否递归
     * @return
     */
    List<Object> previewFileList(String bucketName,String prefix,Boolean recursive) throws Exception;

}
