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

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
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
        context.setVariable("borrow_list", service.getBorrowList());    //这里把Borrow类型的List集合通过context替换给相应的标签
        context.setVariable("book_count",service.getBookList().size());//显示书籍数量
        context.setVariable("student_count",service.getStudentList().size());//显示学生数量
        //懒狗写法，获取个人数和书籍数量都要封装一个集合
        ThymeleafUtil.process("index.html", context, resp.getWriter());//渲染html页面
    }
}
