package com.cole.service.edu.controller.api;

import com.cole.common.base.result.R;
import com.cole.service.edu.entity.Teacher;
import com.cole.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: Cxl
 * @since: 2020-09-24
 **/
//@CrossOrigin
@Api(description="讲师")
@RestController
@RequestMapping("/api/edu/teacher")
public class ApiTeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 2.根据id获取讲师课程信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取讲师")
    @GetMapping("get/{id}")
    public R get(
            @ApiParam(value = "讲师ID", required = true)
            @PathVariable String id) {
        Map<String, Object> map = teacherService.selectTeacherInfoById(id);
        return R.ok().data(map);
    }
    /**
     * 1.获取所有讲师列表
     * @return
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/list")
    public R listAll(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items",list).message("获取全部讲师列表");
    }
}
