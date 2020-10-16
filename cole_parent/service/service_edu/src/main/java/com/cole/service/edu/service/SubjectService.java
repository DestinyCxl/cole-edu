package com.cole.service.edu.service;

import com.cole.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cole.service.edu.entity.vo.SubjectVo;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Cxl
 * @since 2020-09-04
 */
public interface SubjectService extends IService<Subject> {

    void batchImport(InputStream inputStream);

    List<SubjectVo> nestedList();
}
