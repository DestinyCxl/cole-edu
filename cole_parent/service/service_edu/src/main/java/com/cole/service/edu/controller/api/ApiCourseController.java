package com.cole.service.edu.controller.api;

import com.cole.common.base.result.R;
import com.cole.service.base.dto.CourseDto;
import com.cole.service.edu.entity.Course;
import com.cole.service.edu.entity.vo.ChapterVo;
import com.cole.service.edu.entity.vo.WebCourseQueryVo;
import com.cole.service.edu.entity.vo.WebCourseVo;
import com.cole.service.edu.service.ChapterService;
import com.cole.service.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Cxl
 * @since: 2020-09-25
 **/
//@CrossOrigin
@Api(description="课程")
@RestController
@RequestMapping("/api/edu/course")
public class ApiCourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    /**
     * 4.课程支付成功后根据课程id更改销售量
     * @param id
     * @return
     */
    @ApiOperation("根据课程id更改销售量")
    @GetMapping("/inner/update-buy-count/{id}")
    public R updateBuyCountById(
            @ApiParam(value = "课程id", required = true)
            @PathVariable String id){
        courseService.updateBuyCountById(id);
        return R.ok();
    }

    /**
     * 3.trade服务根据课程id查询课程信息(跨服务调用)
     * @param courseId
     * @return
     */
    @ApiOperation("根据课程id查询课程信息")
    @GetMapping("/inner/get-course-dto/{courseId}")
    public CourseDto getCourseDtoById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String courseId){
        CourseDto courseDto = courseService.getCourseDtoById(courseId);
        return courseDto;
    }

    /**
     * 2.根据ID查询课程
     * @param courseId
     * @return
     */
    @ApiOperation("根据ID查询课程")
    @GetMapping("get/{courseId}")
    public R getById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String courseId){

        //查询课程信息和讲师信息
        WebCourseVo webCourseVo = courseService.selectWebCourseVoById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);

        return R.ok().data("course", webCourseVo).data("chapterVoList", chapterVoList);
    }

    /**
     * 1.客户端查询课程列表
     * @param webCourseQueryVo
     * @return
     */
    @ApiOperation("课程列表")
    @GetMapping("list")
    public R list(
            @ApiParam(value = "查询对象", required = false)
                    WebCourseQueryVo webCourseQueryVo){
        List<Course> courseList = courseService.webSelectList(webCourseQueryVo);
        return  R.ok().data("courseList", courseList);
    }
}