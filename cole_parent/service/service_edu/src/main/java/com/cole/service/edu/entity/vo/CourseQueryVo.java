package com.cole.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * 搜索对象
 * @author: Cxl
 * @since: 2020-09-17
 **/
@Data
public class CourseQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String teacherId;
    private String subjectParentId;
    private String subjectId;
}