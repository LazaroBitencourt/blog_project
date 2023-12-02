package blog_project.domain.service;

import blog_project.domain.model.Post;

public interface PostService {

    Iterable<Post> findAll();

    Post findById(Long id);

    Post createPost(Post post);

    void updatePost(Long id, Post post);

    void deletePost(Long id);

    Iterable<Post> findPostsByKeyword(String keyword);

}
