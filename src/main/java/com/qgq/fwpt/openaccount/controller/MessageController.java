package com.qgq.fwpt.openaccount.controller;

import com.qgq.fwpt.common.Enums.UserRoleEnum;
import com.qgq.fwpt.common.dto.ResultDto;
import com.qgq.fwpt.openaccount.entity.Message;
import com.qgq.fwpt.openaccount.entity.Teacher;
import com.qgq.fwpt.openaccount.entity.UserLogin;
import com.qgq.fwpt.openaccount.service.MessageService;
import com.qgq.fwpt.openaccount.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2017/04/09
 *
 * @author 繁华
 */
@RestController
@RequestMapping(value = "api/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @Resource
    private TeacherService teacherService;
    // @formatter:off
    /**
     * 消息列表（GET）:api/message/messageList
     * 入参:
     * pageNum: 第几页（默认第一页）
     * pageSize: 每页数据（默认十个）
     * type: 信息类型（0：就业信息  1：通知通告）
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
                    id: 1,
                    title: "标题",
                    content: "内容",
                    type: 1,   信息类型（0：就业信息  1：通知通告）
                    publisher: "发布人",
                    createTime: 1491727903000  发布时间
                 }
               ]
     *     }
     * }
     */
    //@formatter:on
    @GetMapping(value = "messageList")
    public ResultDto messageList() {
        Map<String,List<Message>> map = new HashMap<>();
        map.put("job",messageService.getMessageList(0));
        map.put("inform",messageService.getMessageList(1));
        return new ResultDto(200,"成功",map);

    }

    // @formatter:off
    /**
     * 新增消息（POST）:api/message/creatMessage
     * 入参:
     * {
     *      "title": "标题",
     *      "content": "内容",
     *      "type": 1, 信息类型（0：就业信息  1：通知通告）
     * }
     * 出参:
     * {
     *     "code": 200,
     *     "message": "添加成功",
     *     "data": null
     * }
     */
    //@formatter:on
    @PostMapping(value = "creatMessage")
    public ResultDto creatMessage(@RequestBody Message message, HttpSession session) {
        UserLogin userLogin = (UserLogin) session.getAttribute("user");
        if (UserRoleEnum.STUDENT.getCode().equals(userLogin.getResource())) {
            return new ResultDto(403, "没有权限", "");
        }
        if(UserRoleEnum.ADMIN.getCode().equals(userLogin.getResource())){
            message.setPublisher("管理员");
        }
        if(UserRoleEnum.TEACHER.getCode().equals(userLogin.getResource())) {
            Teacher teacher = teacherService.findById(userLogin.getOpenId());
            message.setPublisher(teacher.getTeaName());
        }
        message.setCreateTime(new Date());
        messageService.insert(message);
        return new ResultDto(200,"发布成功","");
    }


}
