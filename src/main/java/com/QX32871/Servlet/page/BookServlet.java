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
import java.util.ArrayList;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
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
        context.setVariable("book_list", service.getBookList().keySet());  //map集合，这里对书名进行赋值输出
        context.setVariable("book_list_status", new ArrayList<>(service.getBookList().values()));   //这里是对书籍借阅状态进行赋值输出
        ThymeleafUtil.process("books.html", context, resp.getWriter());
    }
}
