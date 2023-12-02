package blog_project.domain.service.Implementation;

import blog_project.domain.model.Post;
import blog_project.domain.service.PostService;
import blog_project.domain.repository.PostRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;

    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Iterable<Post> findAll() {
        List<Post> posts = postRepository.findAll();

        if (posts == null || posts.isEmpty()) {
            throw new EntityNotFoundException("No posts were found.");
        }

        return posts;
    }

    @Override
    public Post findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid post ID.");
        }
        return postRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("This post was not found. post ID: " + id));
    }

    @Override
    public Post createPost(Post post) {
        if(post.getId() != null && postRepository.existsById(post.getId()))
        {
            throw new EntityExistsException("This post already exists.");
        }
        return postRepository.save(post);
    }

    @Override
    public void updatePost(Long id, Post post) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid post ID.");
        }
        Post foundPost = postRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("This post was not found. post ID: " + id));
        BeanUtils.copyProperties(post, foundPost, "id");
        postRepository.save(foundPost);
    }

    @Override
    public void deletePost(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid post ID.");
        }
        postRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findPostsByKeyword(String keyword) {
        List<Post> posts = postRepository.findByKeyword(keyword);
        if (posts == null || posts.isEmpty()) {
            throw new EntityNotFoundException("No posts were found.");
        }
        return posts;
    }


}
