package cn.iocoder.springboot.lab80.oss.controller;

import cn.iocoder.springboot.lab80.oss.common.ResultWrapper;
import cn.iocoder.springboot.lab80.oss.entity.ImageUrlObj;
import cn.iocoder.springboot.lab80.oss.service.FileClientService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
@Api(tags = "API 接口")
public class FileClientController {

    private FileClientService fileClientService;

    @Autowired
    public FileClientController(FileClientService fileClientService) {
        this.fileClientService = fileClientService;
    }

    @ApiOperation(value = "上传文件", notes = "上传文件，这里为任意文件类型")
    @PostMapping("/upload")
    public ResultWrapper upload(MultipartFile multipartFile) {
        String returnUrlPath = fileClientService.upload(multipartFile);
        if(StringUtils.isEmpty(returnUrlPath)){
            return ResultWrapper.error("上传失败！");
        }
        return ResultWrapper.ok(new ResultWrapper().put("fileUrl",returnUrlPath).put("fullFileUrl",fileClientService.getResAccessUrl(returnUrlPath)));
    }

    // swagger 在多文件上传存在坑，获取不到 file 对象
    @ApiOperation(value = "批量上传文件", notes = "批量上传，这里为任意文件类型")
    @PostMapping(value="/batch/upload")
    public ResultWrapper batchUpload(@ApiParam(name="files", value = "图片",required = true)MultipartFile[] files) {
        if(ObjectUtils.isEmpty(files)){
            return ResultWrapper.error("上传失败！");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (MultipartFile multipartFile:
                files) {
            String tempUrlPath = fileClientService.upload(multipartFile);
            if(!StringUtils.isEmpty(tempUrlPath)){
                Map tempMap = new HashMap();
                tempMap.put("fileUrl",tempUrlPath);
                tempMap.put("fullFileUrl",fileClientService.getResAccessUrl(tempUrlPath));
                result.add(tempMap);
                System.out.println("结果为："+result);
            }
        }
        return ResultWrapper.ok(result);
    }

    @ApiOperation(value = "上传图片", notes = "限定为图片类型")
    @PostMapping("/upload/image")
    @ApiImplicitParams({
        //@ApiImplicitParam(name = "multipartFile", value = "图片",required = true,dataTypeClass = MultipartFile.class), // 对于formData失效
        @ApiImplicitParam(name = "isShowThumbImage", value = "是否显示缩略图（0-不显示；1-显示）",required = true,dataTypeClass = Integer.class)
    })
    public ResultWrapper uploadImage(@ApiParam(name="multipartFile", value = "图片",required = true) MultipartFile multipartFile, Integer isShowThumbImage) {
        if(!multipartFile.getContentType().matches("^(image/.*)$")){
            return ResultWrapper.error("不是图片文件！");
        }
        if(isShowThumbImage == 0){
            String returnUrlPath = (String)fileClientService.uploadImage(multipartFile,0);
            if(StringUtils.isEmpty(returnUrlPath)){
                return ResultWrapper.error("上传失败！");
            }
            return ResultWrapper.ok(new ResultWrapper().put("imageUrl",returnUrlPath).put("fullImageUrl",fileClientService.getResAccessUrl(returnUrlPath)));
        }
        if(isShowThumbImage == 1){
            ImageUrlObj imageUrlObj = (ImageUrlObj)fileClientService.uploadImage(multipartFile,1);
            if(ObjectUtils.isEmpty(imageUrlObj)){
                return ResultWrapper.error("上传失败！");
            }
            return ResultWrapper.ok(new ResultWrapper()
                    .put("imageUrl",imageUrlObj.getImagePath())
                    .put("fullImageUrl",fileClientService.getResAccessUrl(imageUrlObj.getImagePath()))
                    .put("thumbImageUrl",imageUrlObj.getThumbImagePath())
                    .put("fullThumbImageUrl",fileClientService.getResAccessUrl(imageUrlObj.getThumbImagePath())));
        }
        return ResultWrapper.error("参数：isShowThumbImage{"+isShowThumbImage+"}，传递有误！");
    }

    @ApiOperation("下载文件")
    @GetMapping("/download")
    @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "fileUrl", value = "文件路径", required = true)
    public void download(String fileUrl, HttpServletResponse response) throws IOException {
        fileClientService.download(fileUrl,response);
    }

    @ApiOperation("删除文件")
    @GetMapping("/delete")
    @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, name = "fileUrl", value = "文件路径", required = true)
    public ResultWrapper delete(@RequestParam("fileUrl") String fileUrl) {
        Boolean delete = fileClientService.delete(fileUrl);
        if(delete){
            return ResultWrapper.ok("删除成功！");
        }
        return ResultWrapper.error("删除失败！");
    }

}
