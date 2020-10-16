package com.cole.service.ucenter.service;

import com.cole.service.base.dto.MemberDto;
import com.cole.service.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cole.service.ucenter.entity.vo.LoginVo;
import com.cole.service.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Cxl
 * @since 2020-10-02
 */
public interface MemberService extends IService<Member> {

    void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    Member getByOpenid(String openid);

    MemberDto getMemberDtoByMemberId(String memberId);

    Integer countRegisterNum(String day);
}
