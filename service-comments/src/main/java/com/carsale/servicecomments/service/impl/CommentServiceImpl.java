package com.carsale.servicecomments.service.impl;

import com.carsale.servicecomments.repo.CommentRepo;
import com.carsale.servicecomments.repo.model.Comment;
import com.carsale.servicecomments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    public List<Comment> fetchAll(){
        return commentRepo.findAll();
    }

    public Comment fetchCommentById(long id){
        final Optional<Comment> maybeComment = commentRepo.findById(id);
        if(maybeComment.isPresent())
            return maybeComment.get();
        else
            throw new IllegalArgumentException("Invalid Comment ID");
    }

    public List<Comment> fetchAllByAd(long id){
        final List<Comment> maybeComments = commentRepo.findByAdId(id);
        if(maybeComments.isEmpty()) throw new IllegalArgumentException("Invalid Ad ID");
        else return maybeComments;
    }

    public long createComment(long customerId, long adId, String description){
        final Comment comment = Comment.builder()
                .customerId(customerId)
                .adId(adId)
                .description(description)
                .build();
        final Comment saveComment = commentRepo.save(comment);

        return saveComment.getId();
    }

    public void updateComment(long id, String description){
        final Optional<Comment> maybeComment = commentRepo.findById(id);
        if(maybeComment.isEmpty()) throw new IllegalArgumentException("Invalid Comment ID");

        final Comment comment = maybeComment.get();
        if(description != null && !description.isBlank()) comment.setDescription(description);

        commentRepo.save(comment);
    }

    public void deleteComment(long id){
        commentRepo.deleteById(id);
    }
}
