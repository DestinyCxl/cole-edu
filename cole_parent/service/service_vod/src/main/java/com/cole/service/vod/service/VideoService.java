package com.cole.service.vod.service;






import com.aliyuncs.exceptions.ClientException;

import java.io.InputStream;
import java.util.List;

/**
 * @author: Cxl
 * @since: 2020-09-21
 **/
public interface VideoService {
    String uploadVideo(InputStream file, String originalFilename);

    void removeVideo(String vodId) throws com.aliyuncs.exceptions.ClientException;

    void removeVideoByIdList(List<String> videoIdList) throws com.aliyuncs.exceptions.ClientException;

    String getPlayAuth(String videoSourceId) throws ClientException;
}
