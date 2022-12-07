package com.QX32871.Service.impl;

import com.QX32871.Dao.BookMapper;
import com.QX32871.Dao.StudentMapper;
import com.QX32871.Entity.Book;
import com.QX32871.Entity.Borrow;
import com.QX32871.Entity.Student;
import com.QX32871.Service.BookService;
import com.QX32871.Utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    /**
     * 调用工具类中的方法从数据库中查询出相应信息
     *
     * @return 返回的是泛型类型为Borrow的一个List集合，里面存储了从数据库里面查询到的所有的借阅信息
     */
    @Override
    public List<Borrow> getBorrowList() {
        //因为SQLSession这个接口继承了AutoCloseable这个接口，所以将它写在try后面的括号里可以让jdk自己释放资源，不用自己动手
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            return mapper.gerBorrowList();
        }
    }

    /**
     * @param id 传入的是借阅的id
     */
    @Override
    public void bookReturn(String id) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.deleteBorrow(id);
        }
    }

    /**
     * 过滤一下已经借出去的书，已经借过的书不能再借
     *
     * @return 使用stream流体系的过滤器过滤，返回的是没有借出去的书，也就是返回上面getBorrowList方法中获取的泛型类型为Borrow的List集合中没有的那些对象
     * 最后使用toList方法把它转换成泛型类型为Book的List集合
     */
    @Override
    public List<Book> getActiveBookList() {
        //先获取哪些书被借了
        Set<Integer> set = new HashSet<>();
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            return mapper.getBookList().stream().filter(book -> !set.contains(book.getBid())).collect(Collectors.toList());
        }
    }

    /**
     * 获取学生列表
     *
     * @return 返回的是学生列表
     */
    @Override
    public List<Student> getStudentList() {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            return mapper.getStudentList();
        }
    }

    /**
     * 插入新借书信息
     *
     * @param sid 学生id
     * @param bid 书籍id
     */
    @Override
    public void addBorrow(int sid, int bid) {
        //由于已经在数据库里面设置里bid是唯一的，同一本书不能同时被借两次，所以不用对书本id重复的情况做判断
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.addBorrow(sid, bid);
        }
    }

    /**
     * 查询所有书籍
     *
     * @return 所有装有所有书籍对象的集合
     */
    @Override
    public Map<Book, Boolean> getBookList() {
        Set<Integer> set = new HashSet<>();
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            Map<Book, Boolean> map = new LinkedHashMap<>();
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            //这里主要是为了显示此书籍被借阅的状态，用到了遍历来逐个判断
            mapper.getBookList().forEach(book -> map.put(book, set.contains(book.getBid())));
            return map;
        }
    }

    /**
     * 删除书籍信息
     *
     * @param bid 前端传入的书籍id
     */
    @Override
    public void deleteBook(int bid) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.deleteBook(bid);
        }
    }

    /**
     * 添加图书信息
     *
     * @param title 前端表单传入的书名
     * @param desc  前端表单传入的书籍简介
     * @param price 前端表单传入的书籍价格
     */
    @Override
    public void addBook(String title, String desc, double price) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.addBook(title, desc, price);
        }
    }
}
