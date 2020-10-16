package com.cole.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cole.common.base.result.R;
import com.cole.service.edu.entity.Teacher;
import com.cole.service.edu.entity.vo.TeacherQueryVo;
import com.cole.service.edu.feign.OssFileService;
import com.cole.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Cxl
 * @since 2020-09-04
 */
@Api(description = "讲师管理")
//@CrossOrigin
@RestController
@RequestMapping("/admin/edu/teacher")
@Slf4j
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private OssFileService ossFileService;
    /**
     * 10. 测试sentinel
     */
    @GetMapping("/message1")
    public String message1() {
        return "message1";
    }

    @GetMapping("/message2")
    public String message2() {
        return "message2";
    }

    /**
     * 9.测试Tomcat并发能力
     */
    @ApiOperation("测试并发")
    @GetMapping("test_concurrent")
    public R testConcurrent(){
        log.info("test_concurrent");
        return R.ok();
    }

    /**
     * 8.测试cloud远程调用oss微服务
     */
    @ApiOperation("测试服务调用")
    @GetMapping("/test")
    public R test(){
        ossFileService.test();
        return R.ok();
    }

    /**
     * 7.根据关键字查询讲师列表
     */
    @ApiOperation("根据关键字查询讲师列表")
    @GetMapping("/list/name/{key}")
    public R selectNameListByKey(
            @ApiParam(value = "关键字",required = true)
            @PathVariable  String key){
        List<Map<String,Object>> nameList = teacherService.selectNameList(key);
        return R.ok().data("items",nameList);
    }

    /**
     * 6.根据id列表批量删除讲师
     */
    @ApiOperation("根据id列表批量删除讲师")
    @DeleteMapping("/removeTeachersById")
    public R removeTeachersById(
            @ApiParam(value = "讲师id列表",required = true)
            @RequestBody List<String> idList){

        boolean result = teacherService.removeByIds(idList);

        if (result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败，数据不存在");
        }
    }
    /**
     * 5.更新教师
     *
     */
    @ApiOperation("根据id查询讲师")
    @GetMapping("/getTeacherById/{id}")
    public R getTeacherById(@ApiParam("需要修改的教师id")@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        if (teacher != null){
            return R.ok().data("items",teacher);
        }else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("更新讲师")
    @PutMapping("/updateTeacher")
    public R updateTeacher(@ApiParam(value = "接收前端教师参数") @RequestBody Teacher teacher){
        boolean result = teacherService.updateById(teacher);
        if (result){
            return R.ok().message("保存成功");
        }else {
            return R.error().message("保存失败");
        }
    }

    /**
     * 4.新增教师
     *
     */
    @ApiOperation("增加讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@ApiParam(value = "接收前端教师参数",required = true) @RequestBody Teacher teacher){
        boolean result = teacherService.save(teacher);
       if (result){
           return R.ok().message("保存成功");
       }else {
           return R.error().message("保存失败");
       }
    }

    /**
     * 3.分页查询
     *
     */
    @ApiOperation("讲师分页列表 ")
    @GetMapping("/pageList/{current}/{limit}")
    public R pageList(@ApiParam(value = "当前页码",required = true)@PathVariable Long current,
                      @ApiParam(value = "每页记录数",required = true)@PathVariable Long limit,
                      @ApiParam("查询对象") TeacherQueryVo teacherQueryVo){

        Page<Teacher> pageParam = new Page<Teacher>(current,limit);
        IPage<Teacher> pageModel = teacherService.selectPage(pageParam,teacherQueryVo);
        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total",total).data("rows",records);

    }

    /**
     * 2.根据id删除教师（逻辑删除）
     *
     */
    @ApiOperation("根据id删除讲师")
    @DeleteMapping("/removeTeacherById/{id}")
    public R removeTeacherById(@ApiParam(value = "接收用户id")@PathVariable String id){

        //删除图片
        teacherService.removeAvatarById(id);
        //删除讲师
        boolean result = teacherService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("数据不存在");
        }
    }

    /**
     * 1.查询所有教师
     *
     */
    @ApiOperation("查询所有讲师")
    @GetMapping("/getAllTeacher")
    public R getAllTeacher(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items",list);
    }

}

