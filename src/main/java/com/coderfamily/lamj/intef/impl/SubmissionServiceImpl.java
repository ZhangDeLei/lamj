package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.dao.SubmissionMapper;
import com.coderfamily.lamj.intef.ISubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangDL
 * @date 2018/3/14 15:31
 */
@Service
@Transactional
public class SubmissionServiceImpl implements ISubmissionService {
    @Autowired
    private SubmissionMapper submissionMapper;
}
