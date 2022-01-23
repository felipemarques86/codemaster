package pt.codemaster.adt;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Comment implements IPublisher {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private EndUser author;
    private Date date;
    private String content;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> replies = new ArrayList<>();
    @ManyToOne
    @JsonIgnore
    private Code code;

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

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    @Override
    public void notifySubscribers(NotificationEvent event) {
        this.code.notifySubscribers(event);
    }

    @Override
    public void subscribe(EndUser user) {
        code.subscribe(user);
    }
}
