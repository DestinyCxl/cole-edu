package com.cole.service.cms.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Cxl
 * @since: 2020-09-29
 **/
@Data
public class AdVo implements Serializable {

    private static final long serialVersionUID=1L;
    private String id;
    private String title;
    private Integer sort;
    private String type;
}