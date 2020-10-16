package com.cole.service.edu.controller.api;

import com.cole.common.base.result.R;
import com.cole.common.base.util.JwtInfo;
import com.cole.common.base.util.JwtUtils;
import com.cole.service.edu.entity.vo.CourseCollectVo;
import com.cole.service.edu.service.CourseCollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Cxl
 * @since: 2020-10-11
 **/
//@CrossOrigin
@Api(description="收藏列表")
@RestController
@RequestMapping("/api/edu/course-collect")
@Slf4j
public class ApiCourseCollectController {

    @Autowired
    private CourseCollectService courseCollectService;

    /**
     * 1.判断是否收藏该课程
     * @param courseId
     * @param request
     * @return
     */
    @ApiOperation(value = "判断是否收藏")
    @GetMapping("/auth/is-collect/{courseId}")
    public R isCollect(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        boolean isCollect = courseCollectService.isCollect(courseId, jwtInfo.getId());
        return R.ok().data("isCollect", isCollect);
    }

    /**
     * 2.收藏该课程
     * @param courseId
     * @param request
     * @return
     */
    @ApiOperation(value = "收藏课程")
    @PostMapping("/auth/save/{courseId}")
    public R save(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        courseCollectService.saveCourseCollect(courseId, jwtInfo.getId());
        return R.ok();
    }

    /**
     * 3.在个人信息页面获取课程收藏列表
     * @param request
     * @return
     */
    @ApiOperation(value = "获取课程收藏列表")
    @GetMapping("/auth/list")
    public R collectList(HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        List<CourseCollectVo> list = courseCollectService.selectListByMemberId(jwtInfo.getId());
        return R.ok().data("items", list);
    }

    /**
     * 4.取消收藏课程
     * @param courseId
     * @param request
     * @return
     */
    @ApiOperation(value = "取消收藏课程")
    @DeleteMapping("auth/remove/{courseId}")
    public R remove(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        boolean result = courseCollectService.removeCourseCollect(courseId, jwtInfo.getId());
        if (result) {
            return R.ok().message("已取消");
        } else {
            return R.error().message("取消失败");
        }
    }
}