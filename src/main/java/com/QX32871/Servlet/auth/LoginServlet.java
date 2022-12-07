package com.QX32871.Servlet.auth;

import com.QX32871.Service.UserService;
import com.QX32871.Service.impl.UserServiceImpl;
import com.QX32871.Utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收到get请求之后把服务器本地资源的login.html通过Thymeleaf工具类渲染到前端页面
        Context context = new Context();
        //如果登录失败了的话就不为空，failure就为真，此时在前端中会输出登录失败等字样
        if (req.getSession().getAttribute("login-failure") != null) {
            context.setVariable("failure", true);
            req.getSession().removeAttribute("login-failure");
        }
        //如果已经登录过了，就无需再登录，直接重定向到index
        if (req.getSession().getAttribute("user") != null) {
            resp.sendRedirect("index");
            return;
        }
        ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //调用接口实现类判断登录是否成功
        if (service.loginIsSuccess(username, password, req.getSession())) {

            //登录成功，重定向到主页面
            resp.sendRedirect("index");
        } else {
            //登录失败，在session中设置login_failure这一属性，然后交给doGet方法进行进一步的处理（输出提示）
            req.getSession().setAttribute("login-failure", new Object());
            this.doGet(req, resp);
        }
    }
}
