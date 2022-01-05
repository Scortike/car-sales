package com.carsale.servicecomments.service;

import com.carsale.servicecomments.repo.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> fetchAll();
    Comment fetchCommentById(long id) throws IllegalArgumentException;
    List<Comment> fetchAllByAd(long id) throws IllegalArgumentException;
    long createComment(long customerId,
                       long adId,
                       String description);
    void updateComment(long id, String description) throws IllegalArgumentException;
    void deleteComment(long id);
}
