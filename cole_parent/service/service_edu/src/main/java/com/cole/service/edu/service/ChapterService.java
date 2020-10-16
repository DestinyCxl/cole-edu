package com.cole.service.edu.service;

import com.cole.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cole.service.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Cxl
 * @since 2020-09-04
 */
public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterVo> nestedList(String courseId);
}
