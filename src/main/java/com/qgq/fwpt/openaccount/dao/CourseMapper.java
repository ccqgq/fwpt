package com.qgq.fwpt.openaccount.dao;

import com.qgq.fwpt.openaccount.entity.Course;
import com.qgq.fwpt.openaccount.entity.CourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseMapper {
    long countByExample(CourseExample example);

    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    List<Course> selectByExample(CourseExample example);

    List<Course> selectMyCou(@Param("stuId") Integer stuId);

    List<Course> selectCou(@Param("status") Integer status,
                           @Param("stuId") Integer stuId);

    Course selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}