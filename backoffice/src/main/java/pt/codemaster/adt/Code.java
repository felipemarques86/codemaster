package pt.codemaster.adt;


import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Code {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private EndUser author;
    @Enumerated(EnumType.STRING)
    private LanguageEnum language;
    private String code;
    @OneToMany
    private List<Comment> commentList = new ArrayList<>();
    private double score;

    public Code(LanguageEnum language, String code) {
        this.language = language;
        this.code = code;
    }

    public Code() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public EndUser getAuthor() {
        return author;
    }

    public void setAuthor(EndUser author) {
        this.author = author;
    }

    public void addComment(int linha, String commentText, EndUser author) {
        Comment comment = new Comment();
        comment.setDate(new Date());
        comment.setContent(commentText);
        comment.setAuthor(author);

        if(code == null) {
            code = "";
        }
        String[] split = code.split("\n");
        if(split.length < linha) {
            linha = 1;
        }
        split[linha-1] = split[linha-1] + "/**<comment>" + comment.getId() + "</comment>**/";
        this.code = StringUtils.joinWith("\n", split);

        this.commentList.add(comment);
    }
}
