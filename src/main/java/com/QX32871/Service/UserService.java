package com.QX32871.Service;

import jakarta.servlet.http.HttpSession;

public interface UserService {
    //判断用户是否登录成功
    //参数分别表示，servlet传入的用户名，密码和要
    boolean loginIsSuccess(String username, String password, HttpSession session);

    //修改用户名和密码
    void changePassword(String username, String password);
}
