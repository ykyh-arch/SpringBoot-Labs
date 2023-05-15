package cn.iocoder.springboot.lab80.oss.controller;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.springboot.lab80.oss.common.BusinessException;
import cn.iocoder.springboot.lab80.oss.core.OssTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * S3 oss 测试控制器
 *
 */
@RestController
@RequestMapping("/oss-test")
public class S3ossDemoController {

    private static final String DEFALUT_BUCKETNAME = "tong-one";

    @Autowired
    private OssTemplate ossTemplate;

    /**
     * 创建桶
     * @param bucketName
     * @return
     */
    @GetMapping("/createBucket")
    public String createBucket(String bucketName) throws Exception {
        ossTemplate.createBucket(StrUtil.blankToDefault(bucketName,DEFALUT_BUCKETNAME));
        return "创建成功";
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam(name = "file") MultipartFile file) throws Exception {
        return upload(DEFALUT_BUCKETNAME,file);
    }

    private String upload(String bucketName, MultipartFile file) throws Exception {
        if (file == null) {
            throw new BusinessException("上传文件不能为空");
        }
        String orgfileName = file.getOriginalFilename();
        String fileNewName= UUID.randomUUID().toString() +orgfileName.substring(orgfileName.lastIndexOf('.'));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String objectName = sdf.format(new Date()) + "/" + fileNewName;
        try {
            InputStream in = file.getInputStream();
            ossTemplate.putObject(DEFALUT_BUCKETNAME,objectName,in);
            in.close();
        } catch (Exception e) {
            throw new BusinessException("上传失败");
        }

        return "上传文件成功！";
    }

}