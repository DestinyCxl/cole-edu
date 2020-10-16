package com.cole.service.edu.mapper;

import com.cole.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author Cxl
 * @since 2020-09-04
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

}
