package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Column;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnMapping {

    @Select("select * from columns")
    List<Column> getAllColumn();

    @Select("select * from columns where id=#{columnId}")
    Column findOneByColumnId(@Param("columnId") int columnId);

    @Select("select blog_id from blog_column where column_id=#{columnId} limit #{offset},7")
    List<Integer> findBlogIdsByColumnId(@Param("columnId") int columnId,@Param("offset") int offset);

}
