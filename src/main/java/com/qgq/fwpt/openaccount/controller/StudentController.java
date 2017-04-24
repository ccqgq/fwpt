package com.qgq.fwpt.openaccount.controller;

import com.qgq.fwpt.common.Enums.UserRoleEnum;
import com.qgq.fwpt.common.dto.ResultDto;
import com.qgq.fwpt.openaccount.entity.StuCou;
import com.qgq.fwpt.openaccount.entity.Student;
import com.qgq.fwpt.openaccount.entity.UserLogin;
import com.qgq.fwpt.openaccount.service.CourseService;
import com.qgq.fwpt.openaccount.service.StuCouService;
import com.qgq.fwpt.openaccount.service.StudentService;
import com.qgq.fwpt.openaccount.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created on 2017/04/08
 *
 * @author 繁华
 */
@RestController
@RequestMapping("/api/stu")
public class StudentController {

    @Resource
    private CourseService courseService;
    @Resource
    private StudentService studentService;
    @Resource
    private StuCouService stuCouService;

    @GetMapping(value = "couList")
    public ResultDto couList(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        if(!UserRoleEnum.STUDENT.getCode().equals(userLogin.getResource())) {
            new ResultDto(403,"未登录","");
        }
        //获取所有的课程
        return new ResultDto(200, "", courseService.coulist(pageNum, pageSize, 1,userLogin.getOpenId()));
    }


    @GetMapping(value = "myCouList")
    public ResultDto myCouList(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        if(!UserRoleEnum.STUDENT.getCode().equals(userLogin.getResource())) {
            new ResultDto(403,"未登录","");
        }
        return new ResultDto(200,"",courseService.myCouList(pageNum,pageSize,userLogin.getOpenId()));
    }

    @GetMapping(value = "chooseCou")
    public ResultDto chooseCou(HttpSession session, Integer couId) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        if(!UserRoleEnum.STUDENT.getCode().equals(userLogin.getResource())) {
            new ResultDto(403,"未登录","");
        }
        Student student = studentService.findById(userLogin.getOpenId());
        StuCou stuCou = new StuCou();
        stuCou.setCouId(couId);
        stuCou.setStuId(student.getId());
        stuCouService.insert(stuCou);
        return new ResultDto(200, "成功", "");
    }



}
