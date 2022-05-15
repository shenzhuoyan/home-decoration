package com.home_decoration.back_end.baiguo.dao;

import com.home_decoration.back_end.baiguo.domain.YingPin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YingPinDao {
    @Insert("insert into yingpin(name,sex,birth_year,education,school,phone,skill,zhao_id)value(#{name},#{sex},#{birthYear},#{education},#{school},#{phone},#{skill},#{zhaoPin.id})")
    int insert(YingPin yingPin);

    @Select("select * from yingpin order by id desc limit #{left},#{num}")
    List<YingPin> select(int left, int num);

    @Select("select count(*) from yingpin")
    int count();


    @Select("select * from yingpin where zhao_id=#{zhaoId} order by id desc ")
    @Results({
            @Result(column = "id", id = true, property = "id"),
            @Result(column = "zhao_id", property = "zhaoPin",
                    one = @One(
                            select = "com.home_decoration.back_end.baiguo.dao.ZhaoPinDao.selectById"
                    ))})
    List<YingPin> selectByZhaoId(int zhaoId);
}
