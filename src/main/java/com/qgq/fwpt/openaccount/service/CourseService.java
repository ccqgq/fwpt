package com.qgq.fwpt.openaccount.service;

import com.github.pagehelper.PageHelper;
import com.qgq.fwpt.common.model.SimplePageInfo;
import com.qgq.fwpt.openaccount.dao.CourseMapper;
import com.qgq.fwpt.openaccount.entity.Course;
import com.qgq.fwpt.openaccount.entity.CourseExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created on 2017/04/08
 *
 * @author 繁华
 */
@Service
public class CourseService {
    @Resource
    private CourseMapper courseMapper;

    public SimplePageInfo<Course> courPlanList(Integer pageNum,
                                             Integer pageSize,
                                             Integer status) {
        PageHelper.startPage(pageNum,pageSize);
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria()
                .andStatusEqualTo(status);
        return new SimplePageInfo<>(courseMapper.selectByExample(courseExample));
    }

    public void auditCourPlan(Course course){
        courseMapper.updateByPrimaryKeySelective(course);
    }

    public SimplePageInfo<Course> myCouList(Integer pageNum,
                                            Integer pageSize,
                                            Integer stuId){
        PageHelper.startPage(pageNum,pageSize);
        return new SimplePageInfo<>(courseMapper.selectMyCou(stuId));
    }

    public SimplePageInfo<Course> coulist(Integer pageNum,
                                          Integer pageSize,
                                          Integer status,
                                          Integer stuId) {
        PageHelper.startPage(pageNum,pageSize);
        return new SimplePageInfo<>(courseMapper.selectCou(status,stuId));
    }

    public void  insert(Course course) {
        courseMapper.insertSelective(course);
    }

    public List<Course> findByTeaId(Integer id){
        CourseExample courseExample =new CourseExample();
        courseExample.createCriteria()
                .andTeaIdEqualTo(id);
        return courseMapper.selectByExample(courseExample);
    }

}
