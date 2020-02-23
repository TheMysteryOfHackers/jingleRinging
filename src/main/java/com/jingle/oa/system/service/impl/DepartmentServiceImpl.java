package com.jingle.oa.system.service.impl;

import com.jingle.oa.system.entity.Department;
import com.jingle.oa.system.mapper.DepartmentMapper;
import com.jingle.oa.system.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author mowen
 * @since 2019-12-05
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
