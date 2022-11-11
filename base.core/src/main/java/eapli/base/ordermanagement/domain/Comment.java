package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Renan Pedreira
 */
@Embeddable
public class Comment implements ValueObject {
    @Column(name = "comment")
    private String comment;

    public Comment(String comment){
        if (StringPredicates.isNullOrWhiteSpace(comment)){
            throw new IllegalArgumentException("Invalid comment.");
        }

        this.comment=comment;
    }

    public Comment(){}

    @Override
    public String toString() {
        return this.comment;
    }
}
