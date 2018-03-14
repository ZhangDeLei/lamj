package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.dao.SubmissionAuditMapper;
import com.coderfamily.lamj.intef.ISubmissionAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangDL
 * @date 2018/3/14 16:15
 */
@Service
@Transactional
public class SubmissionAuditServiceImpl implements ISubmissionAuditService {
    @Autowired
    private SubmissionAuditMapper submissionAuditMapper;
}
