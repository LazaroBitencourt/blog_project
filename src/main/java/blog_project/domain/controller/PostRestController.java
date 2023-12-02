package blog_project.domain.controller;

import blog_project.domain.model.Post;
import blog_project.domain.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/posts")
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService userService) {
        this.postService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok(post);
    }
    @GetMapping
    public ResponseEntity<Iterable<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post postToCreate) {
        var postCreated = postService.createPost(postToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postCreated.getId()).toUri();
        return ResponseEntity.created(location).body(postCreated);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id,@RequestBody Post postUpdate){
        postService.updatePost(id, postUpdate);
        return ResponseEntity.ok(postUpdate);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{keyword}")
    public ResponseEntity<Iterable<Post>> findPostByKeyword(@RequestParam String keyword){
        return ResponseEntity.ok(postService.findPostsByKeyword(keyword));
    }
}
