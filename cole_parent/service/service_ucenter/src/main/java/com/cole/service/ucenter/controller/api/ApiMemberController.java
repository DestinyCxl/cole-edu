package com.cole.service.ucenter.controller.api;

import com.cole.common.base.result.R;
import com.cole.common.base.result.ResultCodeEnum;
import com.cole.common.base.util.JwtInfo;
import com.cole.common.base.util.JwtUtils;
import com.cole.service.base.dto.MemberDto;
import com.cole.service.base.exception.ColeExcepiton;
import com.cole.service.ucenter.entity.vo.LoginVo;
import com.cole.service.ucenter.entity.vo.RegisterVo;
import com.cole.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Cxl
 * @since: 2020-10-02
 **/
@Api(description = "用户管理")
//@CrossOrigin
@RestController
@RequestMapping("/api/ucenter/member")
@Slf4j
public class ApiMemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 4.根据会员id查询会员信息(跨服务调用)
     * @param memberId
     * @return
     */
    @ApiOperation("根据会员id查询会员信息")
    @GetMapping("inner/get-member-dto/{memberId}")
    public MemberDto getMemberDtoByMemberId(
            @ApiParam(value = "会员ID", required = true)
            @PathVariable String memberId){
        MemberDto memberDto = memberService.getMemberDtoByMemberId(memberId);
        return memberDto;
    }

    /**
     * 3.根据token获取登录信息
     * @param request
     * @return
     */
    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("/get-login-info")
    public R getLoginInfo(HttpServletRequest request){

        try{
            JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
            return R.ok().data("userInfo", jwtInfo);
        }catch (Exception e){
            log.error("解析用户信息失败，" + e.getMessage());
            throw new ColeExcepiton(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
    }

    /**
     * 2.用户登录
     * @param loginVo
     * @return
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token).message("登录成功");
    }

    /**
     * 1.用户注册
     * @param registerVo
     * @return
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }
}