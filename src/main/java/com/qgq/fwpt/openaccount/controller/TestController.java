package com.qgq.fwpt.openaccount.controller;

import com.github.pagehelper.PageHelper;
import com.qgq.fwpt.common.dto.ResultDto;
import com.qgq.fwpt.common.model.SimplePageInfo;
import com.qgq.fwpt.openaccount.dao.CourseMapper;
import com.qgq.fwpt.openaccount.entity.CourseExample;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created on 2017/04/07
 *
 * @author 繁华
 */
@RestController
public class TestController {
    @Resource
    private CourseMapper courseMapper;

    @GetMapping("index")
    public ResultDto index() {
        PageHelper.startPage(1, 10);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria().andIdEqualTo(1);
        return new ResultDto(1, "", new SimplePageInfo<>(courseMapper.selectByExample(courseExample)));
    }
}
