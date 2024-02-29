package com.win.springbootmybatisplusgenerator.cms.user.service.impl;

import com.win.springbootmybatisplusgenerator.cms.user.entity.User;
import com.win.springbootmybatisplusgenerator.cms.user.mapper.UserMapper;
import com.win.springbootmybatisplusgenerator.cms.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-02-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
  
}
