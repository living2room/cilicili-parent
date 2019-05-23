package com.cilicili.comment.domain;

public class LikeCount {
    private Integer likeCountId;

    private Integer userId;

    public Integer getLikeCountId() {
        return likeCountId;
    }

    public void setLikeCountId(Integer likeCountId) {
        this.likeCountId = likeCountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}