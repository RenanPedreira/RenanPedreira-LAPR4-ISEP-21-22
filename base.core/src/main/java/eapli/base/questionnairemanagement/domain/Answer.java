package eapli.base.questionnairemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.*;

@Embeddable
public class Answer implements ValueObject, Comparable<Answer> {
    private static final String PUNCTUATION = ";";

    @Column(name = "Content", columnDefinition = "LONGTEXT")
    private String content;

    public Answer(String content) {
        content.replace(PUNCTUATION, "");

        this.content = content;
    }

    protected Answer() {}

    @Override
    public int compareTo(Answer otherAnswer) {
        return content.compareTo(otherAnswer.content);
    }

    @Override
    public String toString() {
        return content;
    }

    public String getContent() {
        return content;
    }
}
