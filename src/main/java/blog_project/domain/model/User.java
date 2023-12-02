package blog_project.domain.model;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "tab_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String password;
    @Column(name ="user_posts")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;
    @Column(name ="user_comments")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username == null){
            throw new IllegalArgumentException("The USERNAME field is empty; please fill it completely.");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password == null){
            throw new IllegalArgumentException("The PASSWORD field is empty; please fill it completely.");
        }
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
