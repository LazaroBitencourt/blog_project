package blog_project.service;

import blog_project.model.Post;

public interface PostService {

    Iterable<Post> findAll();

    Post findById(Long id);

    Post createPost(Post post);

    void updatePost(Long id, Post post);

    void deletePost(Long id);

    Iterable<Post> findPostsByKeyword(String keyword);

}
