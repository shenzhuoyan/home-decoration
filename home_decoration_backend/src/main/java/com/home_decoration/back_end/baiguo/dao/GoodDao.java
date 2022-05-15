package com.home_decoration.back_end.baiguo.dao;

import com.home_decoration.back_end.baiguo.domain.Good;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodDao {
    @Insert("insert into good(picture,title,content,introduction,price,designer_id)value(#{picture},#{title},#{content},#{introduction},#{price},#{designer.id})")
    int insert(Good good);

    @Delete("delete from good where id=#{id}")
    int delete(int id);

    @Update("update good set title=#{title},picture=#{picture},content=#{content},introduction=#{introduction},price=#{price} where id=#{id}")
    int update(Good good);

    @Select("select * from good where id=#{id}")
    @Results({
            @Result(column = "id",id=true,property = "id"),
            @Result(column = "designer_id", property = "designer",
                    one = @One(select = "com.home_decoration.back_end.baiguo.dao.DesignerDao.selectSimple"))
    })
    Good selectById(int id);


    @Select("select id,picture,title,introduction from good")
    List<Good> selectAll();

    @Select("select * from good")
    @Results({
            @Result(column = "id",id=true,property = "id"),
            @Result(column = "designer_id", property = "designer",
                    one = @One(select = "com.home_decoration.back_end.baiguo.dao.DesignerDao.selectSimple"))
    })
    List<Good> selectDetailAll();
}
