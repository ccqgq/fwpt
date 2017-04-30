package com.qgq.fwpt.openaccount.controller;

import com.qgq.fwpt.common.dto.ResultDto;
import com.qgq.fwpt.openaccount.entity.UserLogin;
import com.qgq.fwpt.openaccount.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017/04/07
 *
 * @author 繁华
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;
    // @formatter:off
    /**
     * 用户登录:/api/user/login
     * 入参(json):
     * {
     *     "number": "账号",
     *     "password": "密码",
     *     "resource": 0 (0:学生  1:教师   2:管理员)
     * }
     * 出参(json):
     * {
     *     "code": 200,
     *     "message": "登录成功",
     *     "data": ""
     * }
     */
    //@formatter:on
    @PostMapping(value = "login")
    public ResultDto userLogin(@RequestBody UserLogin userLogin, HttpSession session) {
        UserLogin sessionUser = userService.login(userLogin);
        if (sessionUser == null) {
            return new ResultDto(1, "密码用户名或角色选择错误", "");
        }
        session.setAttribute("user", sessionUser);
        Map<String, String> map = new HashMap<>();
        map.put("user", userLogin.getNumber());
        return new ResultDto(0, "登录成功", map);
    }

    @PostMapping(value = "logout")
    public ResultDto logout(HttpSession session) {
        session.removeAttribute("user");
        return new ResultDto(200, "", "");
    }

    @PostMapping(value = "revisePassword")
    public ResultDto revisePassword(@RequestBody UserLogin userLogin, HttpSession session) {
        UserLogin sessionUserLogin = (UserLogin) session.getAttribute("user");
        if (sessionUserLogin.getNumber().equals(userLogin.getNumber())) {
            userLogin.setId(sessionUserLogin.getId());
            userService.update(userLogin);
            session.removeAttribute("user");
            return new ResultDto(403, "没有权限", "");
        }
        return new ResultDto(403, "没有权限", "");

    }

}
