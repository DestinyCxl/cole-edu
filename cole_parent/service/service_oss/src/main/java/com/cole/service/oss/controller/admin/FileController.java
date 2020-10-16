package com.cole.service.oss.controller.admin;

import com.cole.common.base.result.R;
import com.cole.common.base.result.ResultCodeEnum;
import com.cole.common.base.util.ExceptionUtils;
import com.cole.service.base.exception.ColeExcepiton;
import com.cole.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author: Cxl
 * @since: 2020-09-09
 **/
@Api(description="阿里云文件管理")
@Slf4j
//@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/oss/file")
public class FileController {
    @Autowired
    private FileService fileService;
    /**
     * 3.在阿里云oss中删除讲师头像
     */
    @ApiOperation(value = "讲师头像删除")
    @DeleteMapping("/remove")
    public R removeFile(
            @ApiParam(value = "要删除的头像文件url路径",required = true)
            @RequestBody String url){
        fileService.removeFile(url);
        return R.ok().message("文件删除成功");
    }

    /**
     * 2.测试cloud远程调用方法
     * @return
     */
    @ApiOperation(value = "测试")
    @GetMapping("test")
    public R test() {
        log.info("oss test被调用");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok();
    }

    /**
     * 文件上传
     * @param file
     */
    @ApiOperation("文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(value = "文件", required = true)
    @RequestParam("file") MultipartFile file,

            @ApiParam(value = "模块", required = true)
            @RequestParam("module") String module) {
        String originalFilename = null;
        String uploadUrl = null;

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            originalFilename = file.getOriginalFilename();
            uploadUrl = fileService.upload(inputStream, module, originalFilename);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new ColeExcepiton(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }


        //返回r对象
        return R.ok().message("文件上传成功").data("url", uploadUrl);
    }
}
