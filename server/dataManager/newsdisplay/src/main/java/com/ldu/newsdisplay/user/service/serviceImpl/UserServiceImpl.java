package com.ldu.newsdisplay.user.service.serviceImpl;

import com.ldu.newsdisplay.user.dao.UserMapper;
import com.ldu.newsdisplay.user.domain.User;
import com.ldu.newsdisplay.user.domain.UserExample;
import com.ldu.newsdisplay.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryUser(User user) {
        UserExample userExample = new UserExample();
        return userMapper.selectByExample(userExample);
    }

    @Override
    public Integer insertUser(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria =  userExample.createCriteria();
        userCriteria.andUserNameEqualTo(user.getUserName());
        if (userMapper.selectByExample(userExample) != null){
            userMapper.insertSelective(user);
            return 1;
        }
        return 0;
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User queryUserByUserName(String userName, String userPassword) {
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria =  userExample.createCriteria();
        userCriteria.andUserNameEqualTo(userName);
        List<User> res =  userMapper.selectByExample(userExample);
        if (res == null) return null;
        User user =  res.get(0);
        if (user.getUserName().equals(userName) && user.getUserPwd().equals(userPassword))
        {
            return user;
        }else {
            return null;
        }


    }
}
