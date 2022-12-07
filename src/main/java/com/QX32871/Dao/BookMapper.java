package com.QX32871.Dao;

import com.QX32871.Entity.Book;
import com.QX32871.Entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {
    //查询已经借了书的信息
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "bid", property = "book_id"),
            @Result(column = "title", property = "book_name"),
            @Result(column = "time", property = "time"),
            @Result(column = "name", property = "student_name"),
            @Result(column = "sid", property = "student_id"),
    })
    @Select("select * from borrow, student, book where borrow.bid = book.bid and student.sid = borrow.sid")
    List<Borrow> gerBorrowList();

    //插入新借书信息
    @Insert("insert into borrow(sid, bid, time) values(#{sid}, #{bid}, NOW())")
    void addBorrow(@Param("sid") int sid, @Param("bid") int bid);   //传入参数，传入sid和bid赋值给同名变量以进行插入操作

    //删除借书信息 还书操作
    @Delete("delete from borrow where id = #{id}")
    void deleteBorrow(String id);

    //查询数据库中所有的书籍
    @Select("select * from book")
    List<Book> getBookList();

    //删除书籍信息
    @Select("delete from book where bid = #{bid}")
    void deleteBook(int bid);   //bid = 传入的bid

    //添加书籍
    @Insert("insert into book(title, `desc`, price) values(#{title}, #{desc}, #{price})")
    void addBook(@Param("title") String title, @Param("desc") String desc, @Param("price") double price);
}
