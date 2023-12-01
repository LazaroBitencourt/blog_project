package blog_project.service;

import blog_project.model.Comment;
import blog_project.model.User;

public interface CommentService {

    Iterable<Comment> findAll();

    Comment findById(Long id);

    void createComment(User user);

    void updateComment(Long id, User user);

    void deleteComment(Long id);
}
