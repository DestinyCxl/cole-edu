package com.cole.service.oss.service;

import java.io.InputStream;

/**
 * @author: Cxl
 * @since: 2020-09-09
 **/
public interface FileService {
    /**
     * 阿里云oss文件删除
     */
    void removeFile(String Url);
    /**
     * 文件上传至阿里云
     */
    String upload(InputStream inputStream, String module, String originalFilename);

}
