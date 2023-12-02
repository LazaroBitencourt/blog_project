package blog_project.domain.service.Implementation;

import blog_project.domain.model.Comment;
import blog_project.domain.repository.CommentRepository;
import blog_project.domain.service.CommentService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Iterable<Comment> findAll() {
        List<Comment> comment = commentRepository.findAll();
        if(comment == null || comment.isEmpty()){
            throw new EntityNotFoundException("No comment were found.");
        }
        return comment;
    }

    @Override
    public Comment findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid comment ID.");
        }
        return commentRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("This comment was not found. comment ID: " + id));
    }

    @Override
    public Comment createComment(Comment comment) {
        if(comment.getId() != null && commentRepository.existsById(comment.getId()))
        {
            throw new EntityExistsException("This comment already exists.");
        }
        return commentRepository.save(comment);
    }

    @Override
    public void updateComment(Long id, Comment comment) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid comment ID.");
        }
        Comment foundComment = commentRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("This comment was not found. comment ID: " + id));
        BeanUtils.copyProperties(comment, foundComment, "id");
        commentRepository.save(foundComment);
    }

    @Override
    public void deleteComment(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid comment ID.");
        }
        commentRepository.deleteById(id);
    }
}
