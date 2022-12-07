package com.QX32871.Servlet.manage;

import com.QX32871.Service.BookService;
import com.QX32871.Service.impl.BookServiceImpl;
import com.QX32871.Utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/add-borrow")
public class AddBorrowServlet extends HttpServlet {
    BookService service;

    @Override
    public void init() throws ServletException {
        service = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        //设置变量,列出可以被添加的书籍和学生
        context.setVariable("book_list", service.getActiveBookList());
        context.setVariable("student_list", service.getStudentList());
        ThymeleafUtil.process("add-borrow.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid = Integer.parseInt(req.getParameter("student"));
        int bid = Integer.parseInt(req.getParameter("book"));//强制类型转换一下，sid和bid都是int类型才能插入数据库
        service.addBorrow(sid, bid);    //添加书籍
        resp.sendRedirect("index");//重定向回首页，直接看结果
    }
}
