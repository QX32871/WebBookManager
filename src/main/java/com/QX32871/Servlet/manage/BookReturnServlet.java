package com.QX32871.Servlet.manage;

import com.QX32871.Service.BookService;
import com.QX32871.Service.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/book_return")
public class BookReturnServlet extends HttpServlet {
    BookService service;

    @Override
    public void init() throws ServletException {
        service = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id"); //获取到输入的id
        service.bookReturn(id); //调用BookServiceImpl里面的方法进行数据库删除操作
        resp.sendRedirect("index"); //删除完之后回到主页
    }
}
