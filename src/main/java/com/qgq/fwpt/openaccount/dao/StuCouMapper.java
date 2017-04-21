package com.qgq.fwpt.openaccount.dao;

import com.qgq.fwpt.openaccount.entity.StuCou;
import com.qgq.fwpt.openaccount.entity.StuCouExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuCouMapper {
    long countByExample(StuCouExample example);

    int deleteByExample(StuCouExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StuCou record);

    int insertSelective(StuCou record);

    List<StuCou> selectByExample(StuCouExample example);

    StuCou selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StuCou record, @Param("example") StuCouExample example);

    int updateByExample(@Param("record") StuCou record, @Param("example") StuCouExample example);

    int updateByPrimaryKeySelective(StuCou record);

    int updateByPrimaryKey(StuCou record);
}