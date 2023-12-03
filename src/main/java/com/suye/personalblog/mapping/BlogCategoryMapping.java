package com.suye.personalblog.mapping;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogCategoryMapping {

    @Select("select category_id from blog_category where blog_id=#{id}")
    List<Integer> findCategoryIdsByBlogId(@Param("id") int id);
}
