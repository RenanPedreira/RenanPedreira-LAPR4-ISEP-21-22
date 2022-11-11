package eapli.base.questionnairemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Message implements ValueObject, Comparable<Message> {

    @Column(name = "Message", columnDefinition = "LONGTEXT")
    private String message;

    public Message(String message) { this.message = message; }

    protected Message() {}



    @Override
    public boolean equals(final Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Message))
            return false;

        final Message that = (Message) obj;
        return this.message.equals(that.message);
    }

    @Override
    public int hashCode() { return this.message.hashCode(); }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(message).append("\n");

        return stringBuilder.toString();
    }

    @Override
    public int compareTo(final Message message){ return this.message.compareTo(message.getMessage());}

    public String getMessage() {
        return message;
    }
}
