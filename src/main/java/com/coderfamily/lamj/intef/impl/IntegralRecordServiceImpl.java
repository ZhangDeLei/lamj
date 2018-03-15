package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.dao.IntegralRecordMapper;
import com.coderfamily.lamj.intef.IIntegralRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangDL
 * @date 2018/3/15 08:35
 */
@Service
@Transactional
public class IntegralRecordServiceImpl implements IIntegralRecordService {
    @Autowired
    private IntegralRecordMapper integralRecordMapper;
}
