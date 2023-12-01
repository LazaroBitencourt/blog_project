package blog_project.service;

import blog_project.model.Post;
import blog_project.model.User;

public interface PostService {

    Iterable<Post> findAll();

    Post findById(Long id);

    void createPost(User user);

    void updatePost(Long id, User user);

    void deletePost(Long id);
}
