package com.home_decoration.back_end.baiguo.dao;

import com.home_decoration.back_end.baiguo.domain.Notice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDao {
    @Insert("insert into notice(title,content)value(#{title},#{content})")
    int insert(Notice notice);

    @Update("update notice set status=#{status} where id=#{id}")
    int updateStatus(int id,int status);

    @Update("update notice set status=1 where status=3")
    void updateTo1();

    @Select("select id,date,title,status from notice where status=#{status} order by id desc limit #{left},#{num}")
    List<Notice> selectAllByStatus(int status,int left,int num);

    @Select("select id,date,title,status from notice where status=#{status} order by id desc")
    List<Notice> selectByStatus(int status);

    @Select("select count(*) from notice where status=#{status}")
    int count(int status);

    @Select("select * from notice where id=#{id} and status>0")
    Notice selectById(int id);

    @Select("select id,date,title,status from notice where status>#{status} order by id desc limit 0,8")
    List<Notice> select(int status);
}
