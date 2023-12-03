package com.suye.personalblog.mapping;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogLabelMapping {

    @Select("select label_id from blog_label where blog_id=#{id}")
    List<Integer> findLabesIdByBlogId(@Param("id") int id);
}
