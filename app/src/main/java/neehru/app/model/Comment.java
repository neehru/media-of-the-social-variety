package neehru.app.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.Cascade;

import javax.annotation.processing.Generated;

@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "commenter_id")
    private User commenter;

    @NotBlank(message = "Your comment is empty!")
    private String comment;

    // constructors

    public Comment(){}

    public Comment (Post post, User commenter, String comment) {
        this.post = post;
        this.commenter = commenter;
        this.comment = comment;
    }

    // getters and setters

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public Post getPost(){
        return this.post;
    }

    public void setPost(Post post){
        this.post = post;
    }

    public User getCommenter(){
        return this.commenter;
    }

    public void setCommenter (User commenter){
        this.commenter = commenter;
    }

    public String getComment(){
        return this.comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

}
