package eapli.base.questionnairemanagement.factory;

import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.questionnairemanagement.domain.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireBuilder {
    private Identifier identifier;
    private Title title;
    private List<Message> messages;
    private List<Section> sectionList;
    private TargetAudience targetAudience;

    public void setIdentifier(String identifier) {
        this.identifier = new Identifier(identifier);
    }

    public void setIdentifier() {
        this.identifier = new IdentifierGenerator().generateIdentifier();
    }

    public void setTitle(String title) {
        this.title = new Title(title);
    }

    public void setMessages(List<String> messages) {
        List<Message> messageList = new ArrayList<>();

        for (String message : messages)
            messageList.add(new Message(message));

        this.messages = messageList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public void setTargetAudience(TargetAudience targetAudience) {
        this.targetAudience = targetAudience;
    }

    public Questionnaire build() {
        return new Questionnaire(identifier, title, messages, sectionList, targetAudience);
    }
}
