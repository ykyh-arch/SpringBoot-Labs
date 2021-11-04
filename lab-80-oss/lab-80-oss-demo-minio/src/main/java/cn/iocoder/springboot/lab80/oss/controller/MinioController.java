package cn.iocoder.springboot.lab80.oss.controller;

import cn.iocoder.springboot.lab80.oss.common.ResponseWrapper;
import cn.iocoder.springboot.lab80.oss.properties.MinioProperties;
import cn.iocoder.springboot.lab80.oss.service.MinioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * minio 控制器，参考文档：http://docs.minio.org.cn/docs/master/java-client-api-reference
 *
 * @author Jaquez
 * @date 2021/11/02 09:47
 */
@RestController
@RequestMapping("/minio")
public class MinioController {

    private static Logger log = LoggerFactory.getLogger(MinioController.class);

    @Autowired
    private MinioProperties minioProperties;

    @Autowired
    private MinioService minioService;


    /**
     * 获取文件列表
     *
     * @param map
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public List<Object> list(ModelMap map) throws Exception {

        return minioService.listObjects(minioProperties.getBucketName(),null,true);
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseWrapper upload(@RequestParam(name = "file", required = false) MultipartFile[] file) throws Exception {

        return ResponseWrapper.success(minioService.upload(minioProperties.getEndpoint(),minioProperties.getBucketName(), file),"上传成功");
    }

    /**
     * 下载文件
     * @param response
     * @param fileName
     */
    @RequestMapping("/download")
    public void download(HttpServletResponse response, @RequestParam("fileName") String fileName) {
        minioService.download(response,minioProperties.getBucketName(),fileName);
    }

    /**
     * 删除文件
     * @param fileName
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseWrapper delete(@RequestParam("fileName") String fileName) {
        Integer delete = minioService.delete(minioProperties.getBucketName(), fileName);
        if(delete > 0)
        return ResponseWrapper.success("","删除成功");
        return ResponseWrapper.error("删除失败");
    }


    /**
     * 生成可以预览的文件链接
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/previewList")
    public List<Object> getPreviewList() throws Exception {

        return  minioService.previewFileList(minioProperties.getBucketName(),null,true);
    }

}