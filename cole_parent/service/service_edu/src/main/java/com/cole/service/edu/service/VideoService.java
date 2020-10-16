package com.cole.service.edu.service;

import com.cole.service.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.netflix.client.ClientException;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Cxl
 * @since 2020-09-04
 */
public interface VideoService extends IService<Video> {

    void removeMediaVideoById(String id);

    void removeMediaVideoByChapterId(String chapterId);

    void removeMediaVideoByCourseId(String chapterId);



}
