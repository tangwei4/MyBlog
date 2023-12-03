package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapping {

    @Select("select * from category")
    List<Category> getAllCategory();

    @Select("select * from category where id=#{id}")
    Category findOneById(@Param("id") int id);

    @Select("select blog_id from blog_category where category_id=#{categoryId} limit #{offset},7")
    List<Integer> findBlogIdsByCategoryId(@Param("categoryId") int labelId,@Param("offset") int offset);

    @Select("select * from category where name=#{name}")
    Category findCategoryByCategoryName(String name);

    @Insert("insert into blog_category(blog_id,category_id) values(#{blogId},#{categoryId})")
    int insertCategorytoBlog(@Param("blogId") int blogId, @Param("categoryId") int categoryId);

    @Insert("insert into category(name,blognum) values(#{name},0)")
    int addCategory(@Param("name") String name);

    @Select("select last_insert_id()")
    int lastCategory();

    @Delete("delete from blog_category where category_id=#{categoryId}")
    int deleteCategoryWithBlogBy(@Param("categoryId") int categoryId);

    @Delete("delete from category where id=#{categoryId}")
    int deleteCategoryById(@Param("categoryId")int categoryId);

    @Update("update category set blognum=blognum+1 where id=#{categoryId}")
    int increaseBlogNum(@Param("categoryId") int categoryId);
}
