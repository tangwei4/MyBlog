package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Visitor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorMapping {
    @Select("select * from visitor")
    List<Visitor> getAllVisitor();

    @Select("select * from visitor where id=#{id}")
    Visitor findOneById(@Param("id")int id);

    @Select("insert into visitor(name,avatar,email,address,isfriend,isadmin)values(#{name},'http://localhost:8080/front/img/avatar.png',#{email},#{address},0,0)")
    void addVisitor(@Param("name") String name,@Param("email") String email,@Param("address") String address);

    @Select("select * from visitor where email=#{email}")
    Visitor findOneByEmail(@Param("email") String email);

    @Select("select * from visitor where isfriend=1")
    List<Visitor> findAllFriends();
}
