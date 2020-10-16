package com.cole.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Cxl
 * @since: 2020-09-25
 **/
@Data
public class WebCourseQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String subjectParentId;
    private String subjectId;
    private String buyCountSort;
    private String gmtCreateSort;
    private String priceSort;
    private Integer type; //价格正序：1，价格倒序：2
    private Integer type1; //创建时间正序：1，创建时间倒序：2
    private Integer type2; //销量正序：1，销量倒序：2

}