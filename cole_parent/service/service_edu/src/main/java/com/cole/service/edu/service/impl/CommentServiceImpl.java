package com.cole.service.edu.service.impl;

import com.cole.service.edu.entity.Comment;
import com.cole.service.edu.mapper.CommentMapper;
import com.cole.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Cxl
 * @since 2020-09-04
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
