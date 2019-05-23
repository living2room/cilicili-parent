package com.cilicili.comment.domain;

import java.util.Date;

public class DeleteLog {
    private Integer deleteLogId;

    private Integer commentId;

    private Integer adminId;

    private Date deleteTime;

    private Integer deleteReasonId;

    public Integer getDeleteLogId() {
        return deleteLogId;
    }

    public void setDeleteLogId(Integer deleteLogId) {
        this.deleteLogId = deleteLogId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getDeleteReasonId() {
        return deleteReasonId;
    }

    public void setDeleteReasonId(Integer deleteReasonId) {
        this.deleteReasonId = deleteReasonId;
    }
}