package com.qgq.fwpt.openaccount.service;

import com.qgq.fwpt.openaccount.dao.StuCouMapper;
import com.qgq.fwpt.openaccount.entity.StuCou;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created on 2017/04/08
 *
 * @author 繁华
 */
@Service
public class StuCouService {

    @Resource
    private StuCouMapper stuCouMapper;

    public void insert(StuCou stuCou) {
        stuCouMapper.insertSelective(stuCou);
    }
}
