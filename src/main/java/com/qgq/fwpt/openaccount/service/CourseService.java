package com.qgq.fwpt.openaccount.service;

import com.github.pagehelper.PageHelper;
import com.qgq.fwpt.common.model.SimplePageInfo;
import com.qgq.fwpt.openaccount.RO.Grade;
import com.qgq.fwpt.openaccount.dao.CourseMapper;
import com.qgq.fwpt.openaccount.entity.Course;
import com.qgq.fwpt.openaccount.entity.CourseExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created on 2017/04/08
 *
 * @author 繁华
 */
@Service
public class CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private RestTemplate restTemplate;

    public SimplePageInfo<Course> courPlanList(Integer pageNum,
                                               Integer pageSize,
                                               Integer status) {
        PageHelper.startPage(pageNum, pageSize);
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria()
                .andStatusEqualTo(status);
        return new SimplePageInfo<>(courseMapper.selectByExample(courseExample));
    }

    public void auditCourPlan(Course course) {
        courseMapper.updateByPrimaryKeySelective(course);
    }

    public SimplePageInfo<Course> myCouList(Integer pageNum,
                                            Integer pageSize,
                                            Integer stuId) {
        PageHelper.startPage(pageNum, pageSize);
        return new SimplePageInfo<>(courseMapper.selectMyCou(stuId));
    }

    public SimplePageInfo<Course> coulist(Integer pageNum,
                                          Integer pageSize,
                                          Integer status,
                                          Integer stuId) {
        PageHelper.startPage(pageNum, pageSize);
        return new SimplePageInfo<>(courseMapper.selectCou(status, stuId));
    }

    public void insert(Course course) {
        courseMapper.insertSelective(course);
    }

    public List<Course> findByTeaId(Integer id) {
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria()
                .andTeaIdEqualTo(id);
        return courseMapper.selectByExample(courseExample);
    }

    /**
     * {
     "number":"学号",
     "password":"密码",
     "year":"2016-2017",（学年：目前仅支持2015-2016、2016-2017）
     "semester":"1" （学期）
     }
     */
    public String postQueryDGrade(Grade grade){
        String url ="http://115.159.110.236:8080/query/api/login";
        String body = "{\"number\":\"" + grade.getNumber() + "\",\"password\":\"" + grade.getPassword() + "\",\"year\":\"" + grade.getYear() + "\",\"semester\":\"" + grade.getSemester() + "\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(body,headers);
        String result = restTemplate.postForObject(url,httpEntity,String.class);
        logger.info("返回的结果："+result);
        return result;
    }
}
