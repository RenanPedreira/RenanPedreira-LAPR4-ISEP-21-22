package eapli.base.questionnairemanagement.application;

import eapli.base.grammarutils.QuestionnaireGrammarLexer;
import eapli.base.grammarutils.QuestionnaireGrammarParser;
import eapli.base.grammarutils.Visitor;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.questionnairemanagement.dto.*;
import eapli.base.questionnairemanagement.persistence.QuestionnaireRepository;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class QuestionnaireValidationService {
    QuestionnaireRepository questionnaireRepository = PersistenceContext.repositories().questionnaireRepository();

    public void validateQuestionnaire(String filePath) throws IOException {
        QuestionnaireGrammarLexer lexer = new QuestionnaireGrammarLexer(CharStreams.fromFileName(filePath));
        CommonTokenStream token = new CommonTokenStream(lexer);
        QuestionnaireGrammarParser parser = new QuestionnaireGrammarParser(token);
        Visitor visitor = new Visitor();
        ParseTree tree = parser.prog();

        visitor.visit(tree);
        Questionnaire questionnaire = QuestionnaireMapper.toQuestionnaire(visitor.getQuestionnaireDTO());
        questionnaireRepository.save(questionnaire);
    }

    public void validateQuestionnaireStringFormat(String questionnaireString) {
        QuestionnaireGrammarLexer lexer = new QuestionnaireGrammarLexer(CharStreams.fromString(questionnaireString));
        CommonTokenStream token = new CommonTokenStream(lexer);
        QuestionnaireGrammarParser parser = new QuestionnaireGrammarParser(token);
        Visitor visitor = new Visitor();
        ParseTree tree = parser.prog();

        visitor.visit(tree);
    }
}
