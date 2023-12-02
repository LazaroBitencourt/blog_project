package blog_project.service;

import blog_project.model.Comment;

public interface CommentService {

    Iterable<Comment> findAll();

    Comment findById(Long id);

    Comment createComment(Comment comment);

    void updateComment(Long id, Comment commet);

    void deleteComment(Long id);
}
