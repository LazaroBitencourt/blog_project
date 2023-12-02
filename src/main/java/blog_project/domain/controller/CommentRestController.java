package blog_project.domain.controller;

import blog_project.domain.model.Comment;
import blog_project.domain.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
@RestController
@RequestMapping("/comment")
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        Comment comment = commentService.findById(id);
        return ResponseEntity.ok(comment);
    }
    @GetMapping
    public ResponseEntity<Iterable<Comment>> findAll() {
        return ResponseEntity.ok(commentService.findAll());
    }
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment commentToCreate) {
        var commentCreated = commentService.createComment(commentToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(commentCreated.getId()).toUri();
        return ResponseEntity.created(location).body(commentCreated);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id,@RequestBody Comment commentUpdate){
        commentService.updateComment(id, commentUpdate);
        return ResponseEntity.ok(commentUpdate);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
