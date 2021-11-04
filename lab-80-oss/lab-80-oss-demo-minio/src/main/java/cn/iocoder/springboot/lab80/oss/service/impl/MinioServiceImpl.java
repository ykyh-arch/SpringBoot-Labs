package cn.iocoder.springboot.lab80.oss.service.impl;

import cn.iocoder.springboot.lab80.oss.common.BusinessException;
import cn.iocoder.springboot.lab80.oss.common.PolicyType;
import cn.iocoder.springboot.lab80.oss.common.ResponseWrapper;
import cn.iocoder.springboot.lab80.oss.service.MinioService;
import com.alibaba.fastjson.JSON;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 实现类
 *
 * @author jaquez
 * @date 2021/11/04 11:26
 **/
@Service
public class MinioServiceImpl implements MinioService {

    private static Logger log = LoggerFactory.getLogger(MinioService.class);

    @Autowired
    private MinioClient minioClient;

    @Override
    public List<Object> listObjects(String bucketName,String prefix,Boolean recursive) throws Exception {
        ListObjectsArgs listObjectsArgs = ListObjectsArgs.builder().bucket(bucketName).prefix(prefix).recursive(recursive).build();
        Iterable<Result<Item>> myObjects = minioClient.listObjects(listObjectsArgs);
        Iterator<Result<Item>> iterator = myObjects.iterator();
        List<Object> items = new ArrayList<>();
        String format = "{'fileName':'%s','fileSize':'%s'}";
        while (iterator.hasNext()) {
            Item item = iterator.next().get();
            items.add(JSON.parse(String.format(format, item.objectName(), formatFileSize(item.size()))));
        }
        return items;
    }

    @Override
    public Object upload(String endpoint,String bucketName, MultipartFile[] file) throws Exception {
        if (file == null || file.length == 0) {
            throw new BusinessException("上传文件不能为空");
        }
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(bucketName).build();
        if (!minioClient.bucketExists(bucketExistsArgs)) {
            MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(bucketName).build();
            minioClient.makeBucket(makeBucketArgs);
            // 参考：https://blogs.qianlongyun.cn/archives/1382.html/
            SetBucketPolicyArgs bucketPolicyArgs = SetBucketPolicyArgs.builder().bucket(bucketName).config(PolicyType.READ_ONLY.getValue()).build();
            minioClient.setBucketPolicy(bucketPolicyArgs);
        }

        List<String> orgfileNameList = new ArrayList<>(file.length);

        for (MultipartFile multipartFile : file) {
            String orgfileName = multipartFile.getOriginalFilename();
            String fileNewName= UUID.randomUUID().toString() +orgfileName.substring(orgfileName.lastIndexOf('.'));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String objectName = sdf.format(new Date()) + "/" + fileNewName;

            try {
                InputStream in = multipartFile.getInputStream();
                PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(in, in.available(), -1).build();
                minioClient.putObject(putObjectArgs);
                in.close();
                String fullFilePath = endpoint + "/" + bucketName + "/" + objectName;
                orgfileNameList.add(fullFilePath);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new BusinessException("上传失败");
            }
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("bucketName", bucketName);
        data.put("fileName", orgfileNameList);
        return data;
    }

    @Override
    public void download(HttpServletResponse response, String bucketName, String fileName) {
        InputStream in = null;
        try {
            String name = "";
            System.out.println("download-fileName:" + fileName);
            // 文件名截取
            if(fileName.lastIndexOf("/") != -1){
                name = fileName.substring(fileName.lastIndexOf("/") + 1);
            }else{
                name = fileName;
            }
            StatObjectArgs statObjectArgs = StatObjectArgs.builder().bucket(bucketName).object(fileName).build();
            // 对象元数据
            ObjectStat stat = minioClient.statObject(statObjectArgs);
            response.setContentType(stat.contentType());
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(bucketName).object(fileName).build();
            in = minioClient.getObject(getObjectArgs);
            IOUtils.copy(in, response.getOutputStream());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException("下载失败");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    @Override
    public Integer delete(String bucketName, String fileName) {
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build();
            minioClient.removeObject(removeObjectArgs);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException("删除失败");
        }
        return 1;
    }

    @Override
    public List<Object> previewFileList(String bucketName,String prefix,Boolean recursive) throws Exception {
        ListObjectsArgs listObjectsArgs = ListObjectsArgs.builder().bucket(bucketName).prefix(prefix).recursive(recursive).build();
        Iterable<Result<Item>> myObjects = minioClient.listObjects(listObjectsArgs);
        Iterator<Result<Item>> iterator = myObjects.iterator();
        List<Object> items = new ArrayList<>();
        String format = "{'fileName':'%s'}";
        while (iterator.hasNext()) {
            Item item = iterator.next().get();
            System.out.println("item.objectName():" + item.objectName());
            GetPresignedObjectUrlArgs getPresignedObjectUrlArgs = GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucketName).object(item.objectName()).build();
            String filePath = minioClient.getPresignedObjectUrl(getPresignedObjectUrlArgs);
            items.add(JSON.parse(String.format(format, filePath)));
        }
        return  items;
    }

    /**
     * 显示文件大小信息单位
     * @param fileSize
     * @return
     */
    private static String formatFileSize(long fileSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileSize == 0) {
            return wrongSize;
        }
        if (fileSize < 1024) {
            fileSizeString = df.format((double) fileSize) + " B";
        } else if (fileSize < 1048576) {
            fileSizeString = df.format((double) fileSize / 1024) + " KB";
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format((double) fileSize / 1048576) + " MB";
        } else if (fileSize < 1099511627776L) {
            fileSizeString = df.format((double) fileSize / 1073741824) + " GB";
        }else{
            fileSizeString = df.format((double) fileSize / 1099511627776L) + " TB";
        }
        return fileSizeString;
    }
}
