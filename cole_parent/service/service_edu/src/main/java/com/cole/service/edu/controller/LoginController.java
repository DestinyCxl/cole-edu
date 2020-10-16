package com.cole.service.edu.controller;

import com.cole.common.base.result.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author Cxl
 */
//@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {
    /**
     *3.用户登出
     */
    @PostMapping("/logout")
    public R logout() {
        return R.ok();
    }

       /**
     *2.验证，获取用户信息
     */
    @GetMapping("/info")
    public R info(){
        return R.ok()
                .data("name","admin")
                .data("roles","[zhangsan,lisi,wangwu]")
                .data("avatar","https://himg.bdimg.com/sys/portrait/item/pp.1.2b59db13.sB25eIL4hjE1Y3lnOsyN8Q?_t=1599489045499");
    }


    /**
     *1.登录
     */
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }



}
