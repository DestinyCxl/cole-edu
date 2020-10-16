package com.cole.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Cxl
 * @since: 2020-10-02
 **/
@Data
public class RegisterVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nickname;
    private String mobile;
    private String password;
    private String code;
}