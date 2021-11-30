package pt.codemaster.adt;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Code {
    private LanguageEnum language;
    private String code;
    private List<Comment> commentList = new ArrayList<>();
    private double score;

    public Code(LanguageEnum language, String code) {
        this.language = language;
        this.code = code;
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

    public void addComment(int linha, String commentText, EndUser author) {
        Comment comment = new Comment();
        comment.setContent(commentText);
        comment.setAuthor(author);

        //save comment

        if(code == null) {
            code = "";
        }
        String[] split = code.split("\n");
        if(split.length < linha) {
            linha = 1;
        }
        split[linha-1] = split[linha-1] + "/*ID_COMMENT=" + comment.getId() + "*/";
        this.code = StringUtils.joinWith("\n", split);
    }
}
