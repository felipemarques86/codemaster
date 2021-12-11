package pt.codemaster.adt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private EndUser author;
    private Date date;
    private String content;
    @OneToMany
    private List<Comment> replies = new ArrayList<>();

    public Comment() {
    }

    public EndUser getAuthor() {
        return author;
    }

    public void setAuthor(EndUser author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }

    public void addReply(String content, EndUser user1) {
        Comment comment = new Comment();
        comment.setDate(new Date());
        comment.setAuthor(user1);
        comment.setContent(content);

        replies.add(comment);
    }
}
