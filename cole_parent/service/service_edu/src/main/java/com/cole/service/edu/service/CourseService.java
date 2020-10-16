package com.cole.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cole.service.base.dto.CourseDto;
import com.cole.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cole.service.edu.entity.from.CourseInfoForm;
import com.cole.service.edu.entity.vo.*;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Cxl
 * @since 2020-09-04
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);


    IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo);

    boolean removeCoverById(String id);

    boolean removeCourseById(String id);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    List<Course> webSelectList(WebCourseQueryVo webCourseQueryVo);

    //获取课程信息并更新浏览量
    WebCourseVo selectWebCourseVoById(String id);

    List<Course> selectHotCourse();

    CourseDto getCourseDtoById(String courseId);

    void updateBuyCountById(String id);
}
