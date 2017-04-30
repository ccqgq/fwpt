package com.qgq.fwpt.openaccount.controller;

import com.qgq.fwpt.common.Enums.UserRoleEnum;
import com.qgq.fwpt.common.dto.ResultDto;
import com.qgq.fwpt.openaccount.entity.Course;
import com.qgq.fwpt.openaccount.entity.Teacher;
import com.qgq.fwpt.openaccount.entity.UserLogin;
import com.qgq.fwpt.openaccount.service.CourseService;
import com.qgq.fwpt.openaccount.service.TeacherService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created on 2017/04/08
 *
 * @author 繁华
 */
@RestController
@RequestMapping("/api/tea")
public class TeacherController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private CourseService courseService;


    @PostMapping(value = "addCou")
    public ResultDto add(@RequestBody Course course, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        Teacher teacher = teacherService.findById(userLogin.getOpenId());
        if (teacher == null) {
            return new ResultDto(403, "没有权限", "");
        }
        course.setCollege(teacher.getCollege());
        course.setTeaId(teacher.getId());
        course.setTaeName(teacher.getTeaName());
        course.setStatus(0);
        courseService.insert(course);
        return new ResultDto(200, "", "");
    }

    @GetMapping(value = "couList")
    public ResultDto couList(HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        if (UserRoleEnum.TEACHER.getCode().equals(userLogin.getResource())) {
            return new ResultDto(200, "", courseService.findByTeaId(userLogin.getOpenId()));
        }
        return new ResultDto(403, "", "");
    }

}
