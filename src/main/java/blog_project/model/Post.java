package blog_project.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name ="tab_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)  // TO DO faça o tratamento de exceção para caso o TITLE e o AUTHOR serem nulos.
    private String title;
    private String content;
    @Column(nullable = false)
    private User author;
    private Date creationDate;
    @Column(name = "tab_comments")
    @OneToMany(fetch = FetchType.EAGER)
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}