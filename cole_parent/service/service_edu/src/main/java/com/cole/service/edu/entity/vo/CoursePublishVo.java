package com.cole.service.edu.entity.vo;

import lombok.Data;

/**
 * @author: Cxl
 * @since: 2020-09-17
 **/
@Data
public class CoursePublishVo {

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectParentTitle;
    private String subjectTitle;
    private String teacherName;
    //只用于显示
    private String price;
}

