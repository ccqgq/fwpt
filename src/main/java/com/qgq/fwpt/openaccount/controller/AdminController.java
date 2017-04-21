package com.qgq.fwpt.openaccount.controller;


import com.qgq.fwpt.common.Enums.UserRoleEnum;
import com.qgq.fwpt.common.dto.ResultDto;
import com.qgq.fwpt.openaccount.entity.Course;
import com.qgq.fwpt.openaccount.entity.Student;
import com.qgq.fwpt.openaccount.entity.Teacher;
import com.qgq.fwpt.openaccount.entity.UserLogin;
import com.qgq.fwpt.openaccount.service.CourseService;
import com.qgq.fwpt.openaccount.service.StudentService;
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
@RequestMapping(value = "api/admin")
public class AdminController {

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private CourseService courseService;
    // @formatter:off
    /**
     * 新增学生（POST）:api/admin/addStu
     * 入参:
     * {
     *      "stuName": "姓名",
     *      "stuNumber": "学号",
     *      "college": "学院",
     *      "classGrade": "班级"
     * }
     * 出参:
     * {
     *     "code": 200,
     *     "message": "添加成功".
     *     "data": null
     * }
     */
    //@formatter:on
    @PostMapping(value = "addStu")
    public ResultDto addStu(@RequestBody Student student, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        if (!UserRoleEnum.ADMIN.getCode().equals(userLogin.getResource())) {
            return new ResultDto(403, "不是管理员没有权限", "");
        }
        studentService.insertStu(student);
        return new ResultDto(200, "添加成功", "");

    }

    /**
     *
     * 获取学生列表（GET）:api/admin/stuList
     * 入参:
     * pageNum: 第几页（默认第一页）
     * pageSize: 每页数据（默认十个）
     * 出参:
     * {
     *     "code": 200,
     *     "message": "成功".
     *     "data": {
     *         pageNum: 1,
               pageSize: 0,
               size: 0,
               orderBy: null,
               total: 0,
               pages: 1,
               list: [
                 {
                    "id": 1,
                    "stuName": "学生姓名",
                    "stuNumber": "学号",
                    "email": "邮箱",
                    "college": "学院",
                    "grade": "班级",
                    "status": 0 （状态 0:注册  1:未注册)
                 }
               ]
     *     }
     * }
     */
    @GetMapping(value = "stuList")
    public ResultDto stuList(HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        if (!UserRoleEnum.ADMIN.getCode().equals(userLogin.getResource())) {
            return new ResultDto(403, "不是管理员没有权限", "");
        }
        return new ResultDto(200, "查询成功", studentService.stuList(pageNum,pageSize));
    }

    /**
     *
     * 获取学生列表（GET）:api/admin/taeList
     * 入参:
     * pageNum: 第几页（默认第一页）
     * pageSize: 每页数据（默认十个）
     * 出参:
     * {
     *     "code": 200,
     *     "message": "成功".
     *     "data": {
     *         pageNum: 1,
               pageSize: 0,
               size: 0,
               orderBy: null,
               total: 0,
               pages: 1,
               list: [
                 {
                    "id": 1,
                    "teaName": "学生",
                    "teaNumber": "职工号",
                    "email": "邮箱",
                    "college": "学院",
                    "professional": 职称（1：助教 2：讲师 3：教授）,
                    "phone": 电话号码
                 }
               ]
     *     }
     * }
     */
    @GetMapping(value = "taeList")
    public ResultDto taeList(HttpSession session, @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        if (!UserRoleEnum.ADMIN.getCode().equals(userLogin.getResource())) {
            return new ResultDto(403, "不是管理员没有权限", "");
        }
        return new ResultDto(200, "查询成功", teacherService.taeList(pageNum,pageSize));
    }
    // @formatter:off
    /**
     * 删除学生（GET）:api/admin/delStu
     * 入参:
     *  id: 1 (学生的ID,放在url上)
     * 出参:
     * {
     *     "code": 200,
     *     "message": "删除成功".
     *     "data": null
     * }
     */
    //@formatter:on
    @GetMapping(value = "delStu")
    public ResultDto delStu(Integer id, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        if (!UserRoleEnum.ADMIN.getCode().equals(userLogin.getResource())) {
            return new ResultDto(403, "不是管理员没有权限", "");
        }
        studentService.delStu(id);
        return new ResultDto(200, "删除成功", "");
    }

    // @formatter:off
    /**
     * 新增教师（POST）:api/admin/addTea
     * 入参:
     * {
     *      "teaName": "姓名",
     *      "teaNumber": "学号",
     *      "college": "学院",
     *      "professional": "班级",
     *      "phone": "电话",
     *      "email": "邮箱"
     * }
     * 出参:
     * {
     *     "code": 200,
     *     "message": "添加成功".
     *     "data": null
     * }
     */
    //@formatter:on
    @PostMapping(value = "addTea")
    public ResultDto addTea(@RequestBody Teacher teacher, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        if (!UserRoleEnum.ADMIN.getCode().equals(userLogin.getResource())) {
            return new ResultDto(403, "不是管理员没有权限", "");
        }
        teacherService.insert(teacher);
        return new ResultDto(200, "添加成功", "");

    }

    // @formatter:off
    /**
     * 删除老师（GET）:api/admin/delTea
     * 入参:
     *  id: 1 (教师的ID,放在url上)
     * 出参:
     * {
     *     "code": 200,
     *     "message": "删除成功".
     *     "data": null
     * }
     */
    //@formatter:on
    @GetMapping(value = "delTea")
    public ResultDto delTea(Integer id, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        if (!UserRoleEnum.ADMIN.getCode().equals(userLogin.getResource())) {
            return new ResultDto(403, "不是管理员没有权限", "");
        }
        teacherService.del(id);
        return new ResultDto(200, "删除成功", "");
    }

    // @formatter:off
    /**
     * 课程计划列表（GET）:api/admin/courPlanList
     * 入参:
     * pageNum: 第几页（默认第一页）
     * pageSize: 每页数据（默认十个）
     * status: 课程计划状态（默认待审核）
     * 出参:
     * {
     *     "code": 200,
     *     "message": "成功".
     *     "data": null
     * }
     */
    //@formatter:on
    @GetMapping(value = "courPlanList")
    public ResultDto courPlanList(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "0") Integer status, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        if (!UserRoleEnum.ADMIN.getCode().equals(userLogin.getResource())) {
            return new ResultDto(403, "不是管理员没有权限", "");
        }
        return new ResultDto(200, "成功", courseService.courPlanList(pageNum, pageSize, status));
    }

    // @formatter:off
    /**
     * 审核课程（POST）:api/admin/auditCourPlan
     * 入参:
     * {
     *     "id": 1, 课程id
     *     "status": 审核状态 （1：通过 2：不通过））
     * }
     * 出参:
     * {
     *     "code": 200,
     *     "message": "成功".
     *     "data": null
     * }
     */
    //@formatter:on
    @PostMapping(value = "auditCourPlan")
    public ResultDto auditCourPlan(@RequestBody Course course, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        if (!UserRoleEnum.ADMIN.getCode().equals(userLogin.getResource())) {
            return new ResultDto(403, "不是管理员没有权限", "");
        }
        courseService.auditCourPlan(course);
        return new ResultDto(200, "成功", "");
    }

}
