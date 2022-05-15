package com.home_decoration.back_end.baiguo.dao;

import com.home_decoration.back_end.baiguo.domain.ZhaoPin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZhaoPinDao {
    @Insert("insert into zhaopin(title,`describe`,address,education,num,`text`)value(#{title},#{describe},#{address},#{education},#{num},#{text})")
    int insert(ZhaoPin zhaoPin);

    @Update("update zhaopin set status = #{status} where id = #{id}")
    int update(int id,int status);

    @Select("select * from zhaopin where status=#{status} order by id desc limit #{left},#{num}")
    List<ZhaoPin> selectOnePage(int status,int left, int num);

    @Select("select count(*) from zhaopin where status=#{status}")
    int count(int status);

    @Select("select * from zhaopin where id = #{id}")
    ZhaoPin selectById(int id);

}
