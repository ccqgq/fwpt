package com.qgq.fwpt.openaccount.service;

import com.github.pagehelper.PageHelper;
import com.qgq.fwpt.common.Enums.UserRoleEnum;
import com.qgq.fwpt.common.model.SimplePageInfo;
import com.qgq.fwpt.openaccount.dao.StudentMapper;
import com.qgq.fwpt.openaccount.entity.Student;
import com.qgq.fwpt.openaccount.entity.StudentExample;
import com.qgq.fwpt.openaccount.entity.UserLogin;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created on 2017/04/08
 *
 * @author 繁华
 */
@Service
public class StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private UserService userService;

    @Transactional
    public void insertStu(Student student) {
        student.setStatus(1);
        studentMapper.insertSelective(student);
        //登录表中添加
        UserLogin userLogin = new UserLogin();
        userLogin.setNumber(student.getStuNumber());
        userLogin.setPassword(student.getStuNumber());
        userLogin.setOpenId(student.getId());
        userLogin.setResource(UserRoleEnum.STUDENT.getCode());
        userService.insertUser(userLogin);
    }

    @Transactional
    public void delStu(Integer id) {
        studentMapper.deleteByPrimaryKey(id);
        userService.del(id, UserRoleEnum.STUDENT.getCode());
    }

    public SimplePageInfo<Student> stuList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new SimplePageInfo<>(studentMapper.selectByExample(new StudentExample()));
    }

    public Student findById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
