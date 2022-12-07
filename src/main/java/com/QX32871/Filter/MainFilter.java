package com.QX32871.Filter;

import com.QX32871.Entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")    //匹配规则：全部都走这个过滤器
public class MainFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //过滤器，规则为如果请求的是登录页面才放行，如果是其他页面则看当前用户是否存在于session中，如果不存在则重定向回登录页面
        String url = req.getRequestURI().toString();    //获取页面url
        if (!url.contains("/static/") && !url.endsWith("login")) {  //如果请求的既不是静态资源，也不是登录页面，那就进行拦截
            //先在session中查找看看有没有这个user
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                //如果没有这个用户，则重定向到登录页面
                res.sendRedirect("login");
                return;
            }
        }
        chain.doFilter(req, res);
    }
}
