package com.cole.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Cxl
 * @since: 2020-09-20
 **/
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
    private Integer sort;

    private String videoSourceId;
}