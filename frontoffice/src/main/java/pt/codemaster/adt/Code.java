package pt.codemaster.adt;


import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

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

    public List<Comment> getCommentList() {
        return commentList;
    }

    public EndUser getAuthor() {
        return author;
    }

    public void setAuthor(EndUser author) {
        this.author = author;
    }

    public boolean add(Comment comment) {
        return commentList.add(comment);
    }

    public void addCommentText(int line, Comment comment) {
        if(code == null) {
            code = "";
        }
        String[] split = code.split("\n");
        if(split.length < line) {
            line = 1;
        }
        split[line-1] = split[line-1] + "/**<comment>" + comment.getId() + "</comment>**/";
        this.code = StringUtils.joinWith("\n", split);
    }
}
