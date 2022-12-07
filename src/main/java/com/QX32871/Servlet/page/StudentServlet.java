package com.QX32871.Servlet.page;

import com.QX32871.Entity.User;
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

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    BookService service;

    @Override
    public void init() throws ServletException {
        service = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname", user.getNikename());
        context.setVariable("student_list", service.getStudentList());  //显示学生列表
        ThymeleafUtil.process("students.html", context, resp.getWriter());
    }
}
