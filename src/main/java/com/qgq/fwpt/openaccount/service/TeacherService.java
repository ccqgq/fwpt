package com.qgq.fwpt.openaccount.service;

import com.github.pagehelper.PageHelper;
import com.qgq.fwpt.common.Enums.UserRoleEnum;
import com.qgq.fwpt.common.model.SimplePageInfo;
import com.qgq.fwpt.openaccount.dao.TeacherMapper;
import com.qgq.fwpt.openaccount.entity.Teacher;
import com.qgq.fwpt.openaccount.entity.TeacherExample;
import com.qgq.fwpt.openaccount.entity.UserLogin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created on 2017/04/08
 *
 * @author 繁华
 */
@Service
public class TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private UserService userService;

    @Transactional
    public void insert(Teacher teacher) {
        teacherMapper.insert(teacher);
        UserLogin userLogin = new UserLogin();
        userLogin.setNumber(teacher.getTeaNumber());
        userLogin.setPassword(teacher.getTeaNumber());
        userLogin.setOpenId(teacher.getId());
        userLogin.setResource(UserRoleEnum.TEACHER.getCode());
        userService.insertUser(userLogin);
    }

    @Transactional
    public void del(Integer id) {
        teacherMapper.deleteByPrimaryKey(id);
        userService.del(id,UserRoleEnum.TEACHER.getCode());
    }

    public Teacher findById(Integer id){
        return teacherMapper.selectByPrimaryKey(id);
    }

    public SimplePageInfo taeList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new SimplePageInfo(teacherMapper.selectByExample(new TeacherExample()));
    }
}
