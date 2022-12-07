package com.QX32871.Service;

import com.QX32871.Entity.Book;
import com.QX32871.Entity.Borrow;
import com.QX32871.Entity.Student;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Borrow> getBorrowList();    //查询借书信息

    void bookReturn(String id);//还书操作

    List<Book> getActiveBookList();//过滤一下已经借出去的书，已经借过的书不能再借

    List<Student> getStudentList();//获取学生列表

    void addBorrow(int sid, int bid);//插入新借书信息,传入的参数一个是书籍id一个是学生id

    Map<Book, Boolean> getBookList();//查询所有书籍

    void deleteBook(int bid);   //删除书籍信息

    void addBook(String title, String desc, double price);//添加书籍信息
}
