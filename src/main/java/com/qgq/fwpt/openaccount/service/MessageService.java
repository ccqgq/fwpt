package com.qgq.fwpt.openaccount.service;

import com.qgq.fwpt.openaccount.dao.MessageMapper;
import com.qgq.fwpt.openaccount.entity.Message;
import com.qgq.fwpt.openaccount.entity.MessageExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created on 2017/04/08
 *
 * @author 繁华
 */
@Service
public class MessageService {
    @Resource
    private MessageMapper messageMapper;

    public List<Message> getMessageList (Integer type){
        MessageExample messageExample = new MessageExample();
        messageExample.createCriteria()
                .andTypeEqualTo(type);
        return messageMapper.selectByExample(messageExample);
    }

    public void insert(Message message) {
        messageMapper.insert(message);
    }
}
