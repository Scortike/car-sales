package com.carsale.servicecomments.api;

import com.carsale.servicecomments.api.dto.CommentDto;
import com.carsale.servicecomments.repo.model.Comment;
import com.carsale.servicecomments.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentServiceImpl commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> index(){
        final List<Comment> comments = commentService.fetchAll();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> showById(@PathVariable long id){
        try{
            final Comment comment = commentService.fetchCommentById(id);

            return ResponseEntity.ok(comment);
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("ad/{id}")
    public ResponseEntity<List<Comment>> showCommentsByAdId(@PathVariable long id){
        try{
            final List<Comment> comments = commentService.fetchAllByAd(id);

            return ResponseEntity.ok(comments);
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CommentDto commentDto){
        final long customerId = commentDto.getCustomerId();
        final long adId = commentDto.getAdId();
        final String description = commentDto.getDescription();

        final long commentId = commentService.createComment(customerId, adId, description);
        String commentURI = String.format("/comments/%d", commentId);

        return ResponseEntity.created(URI.create(commentURI)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody CommentDto commentDto){
        final String description = commentDto.getDescription();
        try{
            commentService.updateComment(id, description);

            return ResponseEntity.noContent().build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        commentService.deleteComment(id);

        return ResponseEntity.noContent().build();
    }
}
