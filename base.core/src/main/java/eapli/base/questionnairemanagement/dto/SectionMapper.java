package eapli.base.questionnairemanagement.dto;

import eapli.base.common.domain.model.Description;
import eapli.base.common.domain.model.Identifier;
import eapli.base.common.util.Pair;
import eapli.base.questionnairemanagement.domain.*;
import eapli.base.questionnairemanagement.domain.Number;

import java.util.ArrayList;
import java.util.List;

public class SectionMapper {
    public static SectionDTO toDTO(Section section) {
        String identifier = section.identifier().getIdentifier();
        Integer number = section.number().getNumber();
        String title = section.title().getTitle();
        String description = section.description().getDescription();
        Pair<String, String> obligatoriness = new Pair<>(section.obligatoriness().getType(), section.obligatoriness().getCondition());

        Pair<String, String> repeatability = null;

        if (section.repeatability() != null)
            repeatability = new Pair<>(section.repeatability().getType(), section.repeatability().getCondition());

        List<QuestionDTO> content = new ArrayList<>();

        for (Question question : section.content())
            content.add(QuestionMapper.toDTO(question));

        return new SectionDTO(identifier, number, title, description, obligatoriness, repeatability, content);
    }

    public static SectionDTO toDTO(String identifier,
                                   Integer number,
                                   String title,
                                   String description,
                                   Pair<String, String> obligatoriness,
                                   Pair<String, String> repeatability,
                                   List<QuestionDTO> content) {
        return new SectionDTO(identifier, number, title, description, obligatoriness, repeatability, content);
    }

    public static Section toSection(SectionDTO sectionDTO) {
        List<Question> questionList = new ArrayList<>();

        for (QuestionDTO questionDTO : sectionDTO.getContent())
            questionList.add(QuestionMapper.toQuestion(questionDTO));

        return new Section(new Identifier(sectionDTO.getIdentifier()),
                new Number(sectionDTO.getNumber()),
                new Title(sectionDTO.getTitle()),
                new Description(Description.DescriptionType.SIMPLE_DESCRIPTION.name(), sectionDTO.getDescription()),
                new Obligatoriness(sectionDTO.getObligatoriness().getKey(), sectionDTO.getObligatoriness().getValue()),
                sectionDTO.getRepeatability() != null ? new Repeatability(sectionDTO.getRepeatability().getKey(), sectionDTO.getRepeatability().getValue()) : null,
                questionList);
    }
}
