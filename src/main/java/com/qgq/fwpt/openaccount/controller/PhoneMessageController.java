package com.qgq.fwpt.openaccount.controller;

import com.qgq.fwpt.common.message.SendMessage;
import com.qgq.fwpt.openaccount.RO.Grade;
import com.qgq.fwpt.openaccount.service.CourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created on 2017/05/24
 *
 * @author 繁华
 */
@RestController
@RequestMapping("api/phone")
public class PhoneMessageController {

    @Resource
    private CourseService courseService;

    @PostMapping("message")
    public String message(String string) {
        Grade grade = new Grade();
        String[] arr = string.split(",");
        grade.setNumber(arr[0]);
        grade.setPassword(arr[1]);
        grade.setYear(arr[2]);
        grade.setSemester(arr[3]);
        String json = courseService.postQueryDGrade(grade);
        return SendMessage.sendPerformanceMessage(json);
    }

}
