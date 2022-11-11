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
public class SourceChannel implements ValueObject {
    @Column(name = "sourceChannel")
    private String sourceChannel;

    public SourceChannel(String sourceChannel){
        if (StringPredicates.isNullOrWhiteSpace(sourceChannel)){
            throw new IllegalArgumentException("Invalid source channel.");
        }
        this.sourceChannel=sourceChannel;
    }

    public SourceChannel(){}

    @Override
    public String toString() {
        return this.sourceChannel;
    }
}
