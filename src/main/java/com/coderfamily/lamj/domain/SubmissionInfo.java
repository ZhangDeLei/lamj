package com.coderfamily.lamj.domain;

import com.coderfamily.lamj.model.SubmissionAuditEntity;
import com.coderfamily.lamj.model.SubmissionEntity;

/**
 * @author ZhangDL
 * @date 2018/3/16 13:27
 */
public class SubmissionInfo extends SubmissionEntity {
    private SubmissionAuditEntity process;

    public SubmissionAuditEntity getProcess() {
        return process;
    }

    public void setProcess(SubmissionAuditEntity process) {
        this.process = process;
    }
}
