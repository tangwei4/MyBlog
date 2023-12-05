package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogMapping {

    @Select("select * from blog order by readnum desc limit 0,5")
    List<Blog> mostPopularBlg();

    @Select("select * from blog where id=#{id}")
    Blog findOneBlog(@Param("id") int id);

    @Select("select count(*) from blog where ispublish > 0")
    int blogTotal();

    @Select("select * from blog where ispublish > 0 order by create_time desc limit 0,7")
    List<Blog> recentBlogs();

    @Select("select * from blog where istalk=0 and ispublish > 0 order by create_time desc limit 0,7")
    List<Blog> recentBlogNotShuoShuo();

    @Select("select * from blog where istalk=1 and ispublish > 0 order by create_time desc limit 0,7")
    List<Blog> recentBlogIsShuoShuo();

    @Select("select * from blog where istalk<=1 order by create_time desc limit #{offset},7")
    List<Blog> loadMoreBlogs(@Param("offset")int offset);



    @Select("select * from blog where istalk<=1 and ispublish > 0 order by create_time desc limit #{offset},7")
    List<Blog> loadMoreBlogsHadPublish(@Param("offset")int offset);

    @Select("select * from blog where istalk=0 and ispublish > 0 order by create_time desc limit #{offset},7")
    List<Blog> loadMoreBlogsNotShuoShuo(@Param("offset")int offset);

    @Select("select * from blog where istalk=1 and ispublish > 0 order by create_time desc limit #{offset},7")
    List<Blog> loadMoreBlogsIsShuoShuo(@Param("offset")int offset);

    @Update("update blog set votenum=votenum+1 where id=#{blogId}")
    int increaseVotenum(@Param("blogId") int blogId);

    @Update("update blog set cainum=cainum+1 where id=#{blogId}")
    int increaseCainum(@Param("blogId") int blogId);

    @Select("select * from blog order by create_time desc")
    List<Blog> findAllBlogTimeDesc();

    @Select("select * from blog where istalk=3")
    Blog findArchive();

    @Select("select * from blog where istalk=4")
    Blog findFriends();

    @Select("select * from blog where istalk=5")
    Blog findAboutMe();

    @Select("select * from blog where ispublish > 0 and content like CONCAT('%',#{content},'%') or title like CONCAT('%',#{content},'%') limit 0,7")
    List<Blog> searchContent(@Param("content") String content);

    @Select("select * from blog where ispublish > 0 and content like CONCAT('%',#{content},'%') or title like CONCAT('%',#{content},'%') limit #{offset},7")
    List<Blog> loadMoreSearch(@Param("content") String content,@Param("offset") int offset);

    @Update("update blog set readnum=readnum+1 where id=#{blogId}")
    int increaseReadNum(int blogId);

    @Update("update blog set conmentnum=conmentnum+1 where id=#{blogId}")
    int increaseConmentNum(int blogId);

    @Insert("insert into blog(title,imgurl,describ,content,create_time,readnum,votenum,conmentnum,istalk,cainum,iscomment,ispublish,htmlcontent,imgid) " +
            "values(#{title},#{imgurl},#{describ},#{content},NOW(),0,0,0,#{istalk},0,#{iscomment},#{ispublish},#{htmlcontent},#{imgid})")
    int addBlog(@Param("title") String title, @Param("imgurl") String imgurl, @Param("describ") String describ,
                @Param("content") String content, @Param("istalk") int istalk, @Param("iscomment") int iscomment,
                @Param("ispublish")int ispublish,@Param("htmlcontent")String htmlcontent,@Param("imgid")int imgid);

    @Update("update blog set title=#{title},describ=#{describ},content=#{content},istalk=#{istalk},iscomment=#{iscomment},ispublish=#{ispublish},htmlcontent=#{htmlcontent},imgid=#{imgid} where id=#{blogId}")
    int modifyBlog(@Param("title") String title,  @Param("describ") String describ,
                   @Param("content") String content, @Param("istalk") int istalk, @Param("iscomment") int iscomment,@Param("ispublish")int ispublish,@Param("blogId")int blogId,@Param("htmlcontent")String htmlcontent,@Param("imgid")int imgid);


    @Select("select last_insert_id()")
    int lastBlogID();

    @Delete("delete from blog where id=#{blogId}")
    int deleteBlog(@Param("blogId") int blogId);

    @Delete("delete from blog_label where blog_id=#{blogId}")
    int deleteBlogAndLabelByBlogId(@Param("blogId")int blogId);

    @Delete("delete from blog_category where blog_id=#{blogId}")
    int deleteBlogAndCategoryByBlogId(@Param("blogId") int blogId);


    @Insert("insert into blog(title,imgurl,describ,content,create_time,readnum,votenum,conmentnum,istalk,cainum,iscomment,ispublish)" +
            "values(#{title},#{imgUrl},#{describ},#{content},NOW(),0,0,0,#{isTalk},0,#{isComment},#{isPublish})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int saveBlog(Blog blog);
}
