package com.QX32871.Service.impl;

import com.QX32871.Dao.UserMapper;
import com.QX32871.Entity.User;
import com.QX32871.Service.UserService;
import com.QX32871.Utils.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

/**
 * 此方法实现登录操作
 */
public class UserServiceImpl implements UserService {
    /**
     * @param username 前端传入的用户名
     * @param password 前端传入的密码
     * @param session  传入session
     * @return 如果查询成功就返回true
     */
    @Override
    public boolean loginIsSuccess(String username, String password, HttpSession session) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser(username, password);
            //如果user为空的话就是登录失败
            if (user == null) return false;
            session.setAttribute("user", user);
            return true;
        }
    }

    /**
     * 修改密码
     * @param username  表单中的username
     * @param password  表单中的password
     */
    @Override
    public void changePassword(String username, String password) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.updatePassword(username, password);
        }
    }
}
