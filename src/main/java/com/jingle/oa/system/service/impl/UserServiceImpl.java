package com.jingle.oa.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingle.oa.system.entity.User;
import com.jingle.oa.system.mapper.UserMapper;
import com.jingle.oa.system.service.IUserService;

/**
 *  服务实现类
 * 
 * @Author Jason
 * @Version 1.0, 2019-06-29
 * @See
 * @Since com.jkoss.examination.system.service.impl
 * @Description: TODO
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
