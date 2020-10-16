package com.cole.service.edu.controller.admin;


import com.cole.common.base.result.R;
import com.cole.common.base.result.ResultCodeEnum;
import com.cole.common.base.util.ExceptionUtils;
import com.cole.service.base.exception.ColeExcepiton;
import com.cole.service.edu.entity.vo.SubjectVo;
import com.cole.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Cxl
 * @since 2020-09-04
 */
@Api(description = "课程分类管理")
//@CrossOrigin
@RestController
@RequestMapping("/admin/edu/subject")
@Slf4j
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    /**
     * 2.嵌套数据列表
     */
    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("nested-list")
    public R nestedList(){
        List<SubjectVo> subjectVoList = subjectService.nestedList();
        return R.ok().data("items", subjectVoList);
    }
    /**
     * 1.导入excel
     */
    @ApiOperation("Excel批量导入课程分类")
    @PostMapping("/import")
    public R batchImport(
            @ApiParam(value = "Excel文件",required = true)
            @RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            subjectService.batchImport(inputStream);
            return R.ok().message("批量导入成功");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new ColeExcepiton(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }
}

