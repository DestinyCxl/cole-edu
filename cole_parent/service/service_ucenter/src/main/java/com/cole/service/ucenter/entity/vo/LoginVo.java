package com.cole.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Cxl
 * @since: 2020-10-06
 **/
@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mobile;
    private String password;
}