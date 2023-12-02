package blog_project.domain.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name ="tab_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
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
        if(title == null){
            throw new IllegalArgumentException("The TITLE field is empty; please fill it completely.");
        }
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
        if(author == null){
            throw new IllegalArgumentException("The AUTHOR field is empty; please fill it completely.");
        }
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