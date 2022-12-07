package com.QX32871.Dao;

import com.QX32871.Entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    //在这里访问数据库，根据前端页面表单发送回服务器的username和password来在数据库查询出相应的数据库并存入对象
    @Select("select * from admin where username = #{username} and password = #{password}")
    User getUser(@Param("username") String username, @Param("password") String password);

    //修改用户名和密码
    @Update("update admin set username = #{username}, password = #{password} where id = 1")
    void updatePassword(@Param("username") String username, @Param("password") String password);

//    @Update("update admin set username = #{username}, nickname = #{nickname}, password = #{password}")
}