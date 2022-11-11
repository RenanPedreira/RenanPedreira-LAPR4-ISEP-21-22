// Generated from /home/pico/Documents/ISEP/LAPR4/lei21_22_s4_2dj_3/base.core/QuestionnaireGrammar.g4 by ANTLR 4.10.1
package eapli.base.grammarutils;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QuestionnaireGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		QUESTIONNAIRE_ID=1, SECTION_DELIMITER=2, OBLIGATORINESS_TITLE=3, OBLIGATORINESS_OPTIONS=4, 
		QUETION_DELIMITER1=5, QUETION_DELIMITER2=6, INSTRUCTION_TITLE=7, TYPE_TITLE=8, 
		SELECTION_TYPE_TITLE=9, TABLE_TYPE_TITLE=10, TABLE_DELIMITER=11, END_TABLE_DELIMITER=12, 
		INSERTION_TYPE_TITLE=13, ANSWER_TITLE=14, TITLE=15, WORD=16, NUMBER=17, 
		SPACE=18, END_SENTENCE_PUNCTUATION=19, PUNCTUATION=20, DELIMITERS=21, 
		SPECIAL_CHARACTERS=22, NEWLINE=23, SENTENCE=24;
	public static final int
		RULE_prog = 0, RULE_start = 1, RULE_questionnaire_structure = 2, RULE_questionnaire_introduction_message = 3, 
		RULE_questionnaire_end_message = 4, RULE_section_structure = 5, RULE_section_description = 6, 
		RULE_question_structure = 7, RULE_obligatoriness_field = 8, RULE_repeatability_field = 9, 
		RULE_extra_info_field = 10, RULE_instruction_field = 11, RULE_type_field = 12, 
		RULE_selection_type = 13, RULE_table_type = 14, RULE_insertion_type = 15, 
		RULE_option_list = 16, RULE_option_item = 17, RULE_table = 18, RULE_table_row = 19, 
		RULE_entry = 20, RULE_end_entry = 21, RULE_answer = 22;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "start", "questionnaire_structure", "questionnaire_introduction_message", 
			"questionnaire_end_message", "section_structure", "section_description", 
			"question_structure", "obligatoriness_field", "repeatability_field", 
			"extra_info_field", "instruction_field", "type_field", "selection_type", 
			"table_type", "insertion_type", "option_list", "option_item", "table", 
			"table_row", "entry", "end_entry", "answer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'Obligatoriness'", null, "'{'", "'}'", "'Instructions'", 
			"'Type'", null, null, null, "'|]'", null, "'Answer'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "QUESTIONNAIRE_ID", "SECTION_DELIMITER", "OBLIGATORINESS_TITLE", 
			"OBLIGATORINESS_OPTIONS", "QUETION_DELIMITER1", "QUETION_DELIMITER2", 
			"INSTRUCTION_TITLE", "TYPE_TITLE", "SELECTION_TYPE_TITLE", "TABLE_TYPE_TITLE", 
			"TABLE_DELIMITER", "END_TABLE_DELIMITER", "INSERTION_TYPE_TITLE", "ANSWER_TITLE", 
			"TITLE", "WORD", "NUMBER", "SPACE", "END_SENTENCE_PUNCTUATION", "PUNCTUATION", 
			"DELIMITERS", "SPECIAL_CHARACTERS", "NEWLINE", "SENTENCE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "QuestionnaireGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QuestionnaireGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public StartContext start() {
			return getRuleContext(StartContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			start();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StartContext extends ParserRuleContext {
		public Questionnaire_structureContext questionnaire_structure() {
			return getRuleContext(Questionnaire_structureContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			questionnaire_structure();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questionnaire_structureContext extends ParserRuleContext {
		public Questionnaire_structureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaire_structure; }
	 
		public Questionnaire_structureContext() { }
		public void copyFrom(Questionnaire_structureContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class QuestionnaireContext extends Questionnaire_structureContext {
		public TerminalNode QUESTIONNAIRE_ID() { return getToken(QuestionnaireGrammarParser.QUESTIONNAIRE_ID, 0); }
		public TerminalNode TITLE() { return getToken(QuestionnaireGrammarParser.TITLE, 0); }
		public Questionnaire_introduction_messageContext questionnaire_introduction_message() {
			return getRuleContext(Questionnaire_introduction_messageContext.class,0);
		}
		public Questionnaire_end_messageContext questionnaire_end_message() {
			return getRuleContext(Questionnaire_end_messageContext.class,0);
		}
		public List<Section_structureContext> section_structure() {
			return getRuleContexts(Section_structureContext.class);
		}
		public Section_structureContext section_structure(int i) {
			return getRuleContext(Section_structureContext.class,i);
		}
		public QuestionnaireContext(Questionnaire_structureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterQuestionnaire(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitQuestionnaire(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitQuestionnaire(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaire_structureContext questionnaire_structure() throws RecognitionException {
		Questionnaire_structureContext _localctx = new Questionnaire_structureContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_questionnaire_structure);
		int _la;
		try {
			_localctx = new QuestionnaireContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(QUESTIONNAIRE_ID);
			setState(51);
			match(TITLE);
			setState(52);
			questionnaire_introduction_message();
			setState(54); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(53);
				section_structure();
				}
				}
				setState(56); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMBER );
			setState(58);
			questionnaire_end_message();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questionnaire_introduction_messageContext extends ParserRuleContext {
		public Questionnaire_introduction_messageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaire_introduction_message; }
	 
		public Questionnaire_introduction_messageContext() { }
		public void copyFrom(Questionnaire_introduction_messageContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IntroductionMessageContext extends Questionnaire_introduction_messageContext {
		public List<TerminalNode> SENTENCE() { return getTokens(QuestionnaireGrammarParser.SENTENCE); }
		public TerminalNode SENTENCE(int i) {
			return getToken(QuestionnaireGrammarParser.SENTENCE, i);
		}
		public IntroductionMessageContext(Questionnaire_introduction_messageContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterIntroductionMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitIntroductionMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitIntroductionMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaire_introduction_messageContext questionnaire_introduction_message() throws RecognitionException {
		Questionnaire_introduction_messageContext _localctx = new Questionnaire_introduction_messageContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_questionnaire_introduction_message);
		int _la;
		try {
			_localctx = new IntroductionMessageContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(61); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(60);
				match(SENTENCE);
				}
				}
				setState(63); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SENTENCE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Questionnaire_end_messageContext extends ParserRuleContext {
		public Questionnaire_end_messageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaire_end_message; }
	 
		public Questionnaire_end_messageContext() { }
		public void copyFrom(Questionnaire_end_messageContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EndMessageContext extends Questionnaire_end_messageContext {
		public TerminalNode TITLE() { return getToken(QuestionnaireGrammarParser.TITLE, 0); }
		public List<TerminalNode> SENTENCE() { return getTokens(QuestionnaireGrammarParser.SENTENCE); }
		public TerminalNode SENTENCE(int i) {
			return getToken(QuestionnaireGrammarParser.SENTENCE, i);
		}
		public EndMessageContext(Questionnaire_end_messageContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterEndMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitEndMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitEndMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaire_end_messageContext questionnaire_end_message() throws RecognitionException {
		Questionnaire_end_messageContext _localctx = new Questionnaire_end_messageContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_questionnaire_end_message);
		int _la;
		try {
			_localctx = new EndMessageContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(TITLE);
			setState(67); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(66);
				match(SENTENCE);
				}
				}
				setState(69); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SENTENCE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Section_structureContext extends ParserRuleContext {
		public Section_structureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section_structure; }
	 
		public Section_structureContext() { }
		public void copyFrom(Section_structureContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SectionContext extends Section_structureContext {
		public TerminalNode NUMBER() { return getToken(QuestionnaireGrammarParser.NUMBER, 0); }
		public TerminalNode SECTION_DELIMITER() { return getToken(QuestionnaireGrammarParser.SECTION_DELIMITER, 0); }
		public TerminalNode TITLE() { return getToken(QuestionnaireGrammarParser.TITLE, 0); }
		public Section_descriptionContext section_description() {
			return getRuleContext(Section_descriptionContext.class,0);
		}
		public Obligatoriness_fieldContext obligatoriness_field() {
			return getRuleContext(Obligatoriness_fieldContext.class,0);
		}
		public Repeatability_fieldContext repeatability_field() {
			return getRuleContext(Repeatability_fieldContext.class,0);
		}
		public List<Question_structureContext> question_structure() {
			return getRuleContexts(Question_structureContext.class);
		}
		public Question_structureContext question_structure(int i) {
			return getRuleContext(Question_structureContext.class,i);
		}
		public SectionContext(Section_structureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Section_structureContext section_structure() throws RecognitionException {
		Section_structureContext _localctx = new Section_structureContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_section_structure);
		int _la;
		try {
			_localctx = new SectionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(NUMBER);
			setState(72);
			match(SECTION_DELIMITER);
			setState(73);
			match(TITLE);
			setState(74);
			section_description();
			setState(75);
			obligatoriness_field();
			setState(76);
			repeatability_field();
			setState(78); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(77);
				question_structure();
				}
				}
				setState(80); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUETION_DELIMITER1 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Section_descriptionContext extends ParserRuleContext {
		public Section_descriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section_description; }
	 
		public Section_descriptionContext() { }
		public void copyFrom(Section_descriptionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SectionDescriptionContext extends Section_descriptionContext {
		public List<TerminalNode> SENTENCE() { return getTokens(QuestionnaireGrammarParser.SENTENCE); }
		public TerminalNode SENTENCE(int i) {
			return getToken(QuestionnaireGrammarParser.SENTENCE, i);
		}
		public SectionDescriptionContext(Section_descriptionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterSectionDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitSectionDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitSectionDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Section_descriptionContext section_description() throws RecognitionException {
		Section_descriptionContext _localctx = new Section_descriptionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_section_description);
		int _la;
		try {
			_localctx = new SectionDescriptionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SENTENCE) {
				{
				setState(83); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(82);
					match(SENTENCE);
					}
					}
					setState(85); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SENTENCE );
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Question_structureContext extends ParserRuleContext {
		public Question_structureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_structure; }
	 
		public Question_structureContext() { }
		public void copyFrom(Question_structureContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class QuestionContext extends Question_structureContext {
		public TerminalNode QUETION_DELIMITER1() { return getToken(QuestionnaireGrammarParser.QUETION_DELIMITER1, 0); }
		public TerminalNode NUMBER() { return getToken(QuestionnaireGrammarParser.NUMBER, 0); }
		public TerminalNode QUETION_DELIMITER2() { return getToken(QuestionnaireGrammarParser.QUETION_DELIMITER2, 0); }
		public TerminalNode SENTENCE() { return getToken(QuestionnaireGrammarParser.SENTENCE, 0); }
		public Instruction_fieldContext instruction_field() {
			return getRuleContext(Instruction_fieldContext.class,0);
		}
		public Obligatoriness_fieldContext obligatoriness_field() {
			return getRuleContext(Obligatoriness_fieldContext.class,0);
		}
		public Extra_info_fieldContext extra_info_field() {
			return getRuleContext(Extra_info_fieldContext.class,0);
		}
		public Type_fieldContext type_field() {
			return getRuleContext(Type_fieldContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public QuestionContext(Question_structureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_structureContext question_structure() throws RecognitionException {
		Question_structureContext _localctx = new Question_structureContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_question_structure);
		int _la;
		try {
			_localctx = new QuestionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(QUETION_DELIMITER1);
			setState(90);
			match(NUMBER);
			setState(91);
			match(QUETION_DELIMITER2);
			setState(92);
			match(SENTENCE);
			setState(93);
			instruction_field();
			setState(94);
			obligatoriness_field();
			setState(95);
			extra_info_field();
			setState(96);
			type_field();
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ANSWER_TITLE) {
				{
				setState(97);
				answer();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Obligatoriness_fieldContext extends ParserRuleContext {
		public Obligatoriness_fieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obligatoriness_field; }
	 
		public Obligatoriness_fieldContext() { }
		public void copyFrom(Obligatoriness_fieldContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ObligatorinessFieldContext extends Obligatoriness_fieldContext {
		public TerminalNode OBLIGATORINESS_TITLE() { return getToken(QuestionnaireGrammarParser.OBLIGATORINESS_TITLE, 0); }
		public TerminalNode OBLIGATORINESS_OPTIONS() { return getToken(QuestionnaireGrammarParser.OBLIGATORINESS_OPTIONS, 0); }
		public ObligatorinessFieldContext(Obligatoriness_fieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterObligatorinessField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitObligatorinessField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitObligatorinessField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Obligatoriness_fieldContext obligatoriness_field() throws RecognitionException {
		Obligatoriness_fieldContext _localctx = new Obligatoriness_fieldContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_obligatoriness_field);
		try {
			_localctx = new ObligatorinessFieldContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(OBLIGATORINESS_TITLE);
			setState(101);
			match(OBLIGATORINESS_OPTIONS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Repeatability_fieldContext extends ParserRuleContext {
		public Repeatability_fieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeatability_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterRepeatability_field(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitRepeatability_field(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitRepeatability_field(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Repeatability_fieldContext repeatability_field() throws RecognitionException {
		Repeatability_fieldContext _localctx = new Repeatability_fieldContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_repeatability_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Extra_info_fieldContext extends ParserRuleContext {
		public Extra_info_fieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extra_info_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterExtra_info_field(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitExtra_info_field(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitExtra_info_field(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Extra_info_fieldContext extra_info_field() throws RecognitionException {
		Extra_info_fieldContext _localctx = new Extra_info_fieldContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_extra_info_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Instruction_fieldContext extends ParserRuleContext {
		public Instruction_fieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction_field; }
	 
		public Instruction_fieldContext() { }
		public void copyFrom(Instruction_fieldContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class InstructionFieldContext extends Instruction_fieldContext {
		public TerminalNode INSTRUCTION_TITLE() { return getToken(QuestionnaireGrammarParser.INSTRUCTION_TITLE, 0); }
		public List<TerminalNode> SENTENCE() { return getTokens(QuestionnaireGrammarParser.SENTENCE); }
		public TerminalNode SENTENCE(int i) {
			return getToken(QuestionnaireGrammarParser.SENTENCE, i);
		}
		public InstructionFieldContext(Instruction_fieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterInstructionField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitInstructionField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitInstructionField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instruction_fieldContext instruction_field() throws RecognitionException {
		Instruction_fieldContext _localctx = new Instruction_fieldContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_instruction_field);
		int _la;
		try {
			_localctx = new InstructionFieldContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INSTRUCTION_TITLE) {
				{
				setState(107);
				match(INSTRUCTION_TITLE);
				setState(109); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(108);
					match(SENTENCE);
					}
					}
					setState(111); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SENTENCE );
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_fieldContext extends ParserRuleContext {
		public Type_fieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_field; }
	 
		public Type_fieldContext() { }
		public void copyFrom(Type_fieldContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeFieldContext extends Type_fieldContext {
		public Selection_typeContext selectionType;
		public Table_typeContext tableType;
		public Insertion_typeContext insertionType;
		public TerminalNode TYPE_TITLE() { return getToken(QuestionnaireGrammarParser.TYPE_TITLE, 0); }
		public Selection_typeContext selection_type() {
			return getRuleContext(Selection_typeContext.class,0);
		}
		public Table_typeContext table_type() {
			return getRuleContext(Table_typeContext.class,0);
		}
		public Insertion_typeContext insertion_type() {
			return getRuleContext(Insertion_typeContext.class,0);
		}
		public TypeFieldContext(Type_fieldContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterTypeField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitTypeField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitTypeField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_fieldContext type_field() throws RecognitionException {
		Type_fieldContext _localctx = new Type_fieldContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_type_field);
		try {
			_localctx = new TypeFieldContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(TYPE_TITLE);
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECTION_TYPE_TITLE:
				{
				setState(116);
				((TypeFieldContext)_localctx).selectionType = selection_type();
				}
				break;
			case TABLE_TYPE_TITLE:
				{
				setState(117);
				((TypeFieldContext)_localctx).tableType = table_type();
				}
				break;
			case INSERTION_TYPE_TITLE:
				{
				setState(118);
				((TypeFieldContext)_localctx).insertionType = insertion_type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Selection_typeContext extends ParserRuleContext {
		public Selection_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selection_type; }
	 
		public Selection_typeContext() { }
		public void copyFrom(Selection_typeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SelectionTypeContext extends Selection_typeContext {
		public TerminalNode SELECTION_TYPE_TITLE() { return getToken(QuestionnaireGrammarParser.SELECTION_TYPE_TITLE, 0); }
		public Option_listContext option_list() {
			return getRuleContext(Option_listContext.class,0);
		}
		public SelectionTypeContext(Selection_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterSelectionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitSelectionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitSelectionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Selection_typeContext selection_type() throws RecognitionException {
		Selection_typeContext _localctx = new Selection_typeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_selection_type);
		try {
			_localctx = new SelectionTypeContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(SELECTION_TYPE_TITLE);
			setState(122);
			option_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_typeContext extends ParserRuleContext {
		public Table_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_type; }
	 
		public Table_typeContext() { }
		public void copyFrom(Table_typeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TableTypeContext extends Table_typeContext {
		public TerminalNode TABLE_TYPE_TITLE() { return getToken(QuestionnaireGrammarParser.TABLE_TYPE_TITLE, 0); }
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public TableTypeContext(Table_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterTableType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitTableType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitTableType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_typeContext table_type() throws RecognitionException {
		Table_typeContext _localctx = new Table_typeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_table_type);
		try {
			_localctx = new TableTypeContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(TABLE_TYPE_TITLE);
			setState(125);
			table();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Insertion_typeContext extends ParserRuleContext {
		public Insertion_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertion_type; }
	 
		public Insertion_typeContext() { }
		public void copyFrom(Insertion_typeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class InsertionTypeContext extends Insertion_typeContext {
		public TerminalNode INSERTION_TYPE_TITLE() { return getToken(QuestionnaireGrammarParser.INSERTION_TYPE_TITLE, 0); }
		public InsertionTypeContext(Insertion_typeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterInsertionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitInsertionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitInsertionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Insertion_typeContext insertion_type() throws RecognitionException {
		Insertion_typeContext _localctx = new Insertion_typeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_insertion_type);
		try {
			_localctx = new InsertionTypeContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(INSERTION_TYPE_TITLE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Option_listContext extends ParserRuleContext {
		public Option_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option_list; }
	 
		public Option_listContext() { }
		public void copyFrom(Option_listContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OptionListContext extends Option_listContext {
		public TerminalNode TITLE() { return getToken(QuestionnaireGrammarParser.TITLE, 0); }
		public List<Option_itemContext> option_item() {
			return getRuleContexts(Option_itemContext.class);
		}
		public Option_itemContext option_item(int i) {
			return getRuleContext(Option_itemContext.class,i);
		}
		public OptionListContext(Option_listContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterOptionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitOptionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitOptionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Option_listContext option_list() throws RecognitionException {
		Option_listContext _localctx = new Option_listContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_option_list);
		try {
			int _alt;
			_localctx = new OptionListContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			match(TITLE);
			setState(131); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(130);
					option_item();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(133); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Option_itemContext extends ParserRuleContext {
		public Option_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option_item; }
	 
		public Option_itemContext() { }
		public void copyFrom(Option_itemContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OptionItemContext extends Option_itemContext {
		public TerminalNode NUMBER() { return getToken(QuestionnaireGrammarParser.NUMBER, 0); }
		public TerminalNode SENTENCE() { return getToken(QuestionnaireGrammarParser.SENTENCE, 0); }
		public OptionItemContext(Option_itemContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterOptionItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitOptionItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitOptionItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Option_itemContext option_item() throws RecognitionException {
		Option_itemContext _localctx = new Option_itemContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_option_item);
		try {
			_localctx = new OptionItemContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(NUMBER);
			setState(136);
			match(SENTENCE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableContext extends ParserRuleContext {
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
	 
		public TableContext() { }
		public void copyFrom(TableContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TableContentContext extends TableContext {
		public List<Table_rowContext> table_row() {
			return getRuleContexts(Table_rowContext.class);
		}
		public Table_rowContext table_row(int i) {
			return getRuleContext(Table_rowContext.class,i);
		}
		public TableContentContext(TableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterTableContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitTableContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitTableContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_table);
		int _la;
		try {
			_localctx = new TableContentContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(139); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(138);
				table_row();
				}
				}
				setState(141); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TABLE_DELIMITER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_rowContext extends ParserRuleContext {
		public Table_rowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_row; }
	 
		public Table_rowContext() { }
		public void copyFrom(Table_rowContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ContentContext extends Table_rowContext {
		public End_entryContext end_entry() {
			return getRuleContext(End_entryContext.class,0);
		}
		public List<EntryContext> entry() {
			return getRuleContexts(EntryContext.class);
		}
		public EntryContext entry(int i) {
			return getRuleContext(EntryContext.class,i);
		}
		public ContentContext(Table_rowContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_rowContext table_row() throws RecognitionException {
		Table_rowContext _localctx = new Table_rowContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_table_row);
		try {
			int _alt;
			_localctx = new ContentContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(144); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(143);
					entry();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(146); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(148);
			end_entry();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EntryContext extends ParserRuleContext {
		public EntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry; }
	 
		public EntryContext() { }
		public void copyFrom(EntryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EmptyEntryContext extends EntryContext {
		public TerminalNode TABLE_DELIMITER() { return getToken(QuestionnaireGrammarParser.TABLE_DELIMITER, 0); }
		public EmptyEntryContext(EntryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterEmptyEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitEmptyEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitEmptyEntry(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DefaultEntryContext extends EntryContext {
		public TerminalNode TABLE_DELIMITER() { return getToken(QuestionnaireGrammarParser.TABLE_DELIMITER, 0); }
		public TerminalNode TITLE() { return getToken(QuestionnaireGrammarParser.TITLE, 0); }
		public DefaultEntryContext(EntryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterDefaultEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitDefaultEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitDefaultEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntryContext entry() throws RecognitionException {
		EntryContext _localctx = new EntryContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_entry);
		try {
			setState(153);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new DefaultEntryContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				match(TABLE_DELIMITER);
				setState(151);
				match(TITLE);
				}
				break;
			case 2:
				_localctx = new EmptyEntryContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				match(TABLE_DELIMITER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class End_entryContext extends ParserRuleContext {
		public End_entryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_end_entry; }
	 
		public End_entryContext() { }
		public void copyFrom(End_entryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EmptyEndEntryContext extends End_entryContext {
		public TerminalNode TABLE_DELIMITER() { return getToken(QuestionnaireGrammarParser.TABLE_DELIMITER, 0); }
		public TerminalNode END_TABLE_DELIMITER() { return getToken(QuestionnaireGrammarParser.END_TABLE_DELIMITER, 0); }
		public EmptyEndEntryContext(End_entryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterEmptyEndEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitEmptyEndEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitEmptyEndEntry(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DefaultEndEntryContext extends End_entryContext {
		public TerminalNode TABLE_DELIMITER() { return getToken(QuestionnaireGrammarParser.TABLE_DELIMITER, 0); }
		public TerminalNode TITLE() { return getToken(QuestionnaireGrammarParser.TITLE, 0); }
		public TerminalNode END_TABLE_DELIMITER() { return getToken(QuestionnaireGrammarParser.END_TABLE_DELIMITER, 0); }
		public DefaultEndEntryContext(End_entryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterDefaultEndEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitDefaultEndEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitDefaultEndEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final End_entryContext end_entry() throws RecognitionException {
		End_entryContext _localctx = new End_entryContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_end_entry);
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new DefaultEndEntryContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(155);
				match(TABLE_DELIMITER);
				setState(156);
				match(TITLE);
				setState(157);
				match(END_TABLE_DELIMITER);
				}
				break;
			case 2:
				_localctx = new EmptyEndEntryContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				match(TABLE_DELIMITER);
				setState(159);
				match(END_TABLE_DELIMITER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnswerContext extends ParserRuleContext {
		public AnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answer; }
	 
		public AnswerContext() { }
		public void copyFrom(AnswerContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AnswerContentContext extends AnswerContext {
		public TerminalNode ANSWER_TITLE() { return getToken(QuestionnaireGrammarParser.ANSWER_TITLE, 0); }
		public List<TerminalNode> SENTENCE() { return getTokens(QuestionnaireGrammarParser.SENTENCE); }
		public TerminalNode SENTENCE(int i) {
			return getToken(QuestionnaireGrammarParser.SENTENCE, i);
		}
		public AnswerContentContext(AnswerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).enterAnswerContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionnaireGrammarListener ) ((QuestionnaireGrammarListener)listener).exitAnswerContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionnaireGrammarVisitor ) return ((QuestionnaireGrammarVisitor<? extends T>)visitor).visitAnswerContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerContext answer() throws RecognitionException {
		AnswerContext _localctx = new AnswerContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_answer);
		int _la;
		try {
			_localctx = new AnswerContentContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(ANSWER_TITLE);
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SENTENCE) {
				{
				setState(164); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(163);
					match(SENTENCE);
					}
					}
					setState(166); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SENTENCE );
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0018\u00ab\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u00027\b"+
		"\u0002\u000b\u0002\f\u00028\u0001\u0002\u0001\u0002\u0001\u0003\u0004"+
		"\u0003>\b\u0003\u000b\u0003\f\u0003?\u0001\u0004\u0001\u0004\u0004\u0004"+
		"D\b\u0004\u000b\u0004\f\u0004E\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005O\b\u0005\u000b"+
		"\u0005\f\u0005P\u0001\u0006\u0004\u0006T\b\u0006\u000b\u0006\f\u0006U"+
		"\u0003\u0006X\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"c\b\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0004\u000bn\b\u000b\u000b\u000b\f\u000bo\u0003\u000b"+
		"r\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0003\fx\b\f\u0001\r\u0001\r"+
		"\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0004\u0010\u0084\b\u0010\u000b\u0010\f\u0010\u0085"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0004\u0012\u008c\b\u0012"+
		"\u000b\u0012\f\u0012\u008d\u0001\u0013\u0004\u0013\u0091\b\u0013\u000b"+
		"\u0013\f\u0013\u0092\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0003\u0014\u009a\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0003\u0015\u00a1\b\u0015\u0001\u0016\u0001\u0016\u0004"+
		"\u0016\u00a5\b\u0016\u000b\u0016\f\u0016\u00a6\u0003\u0016\u00a9\b\u0016"+
		"\u0001\u0016\u0000\u0000\u0017\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,\u0000\u0000\u00a5\u0000"+
		".\u0001\u0000\u0000\u0000\u00020\u0001\u0000\u0000\u0000\u00042\u0001"+
		"\u0000\u0000\u0000\u0006=\u0001\u0000\u0000\u0000\bA\u0001\u0000\u0000"+
		"\u0000\nG\u0001\u0000\u0000\u0000\fW\u0001\u0000\u0000\u0000\u000eY\u0001"+
		"\u0000\u0000\u0000\u0010d\u0001\u0000\u0000\u0000\u0012g\u0001\u0000\u0000"+
		"\u0000\u0014i\u0001\u0000\u0000\u0000\u0016q\u0001\u0000\u0000\u0000\u0018"+
		"s\u0001\u0000\u0000\u0000\u001ay\u0001\u0000\u0000\u0000\u001c|\u0001"+
		"\u0000\u0000\u0000\u001e\u007f\u0001\u0000\u0000\u0000 \u0081\u0001\u0000"+
		"\u0000\u0000\"\u0087\u0001\u0000\u0000\u0000$\u008b\u0001\u0000\u0000"+
		"\u0000&\u0090\u0001\u0000\u0000\u0000(\u0099\u0001\u0000\u0000\u0000*"+
		"\u00a0\u0001\u0000\u0000\u0000,\u00a2\u0001\u0000\u0000\u0000./\u0003"+
		"\u0002\u0001\u0000/\u0001\u0001\u0000\u0000\u000001\u0003\u0004\u0002"+
		"\u00001\u0003\u0001\u0000\u0000\u000023\u0005\u0001\u0000\u000034\u0005"+
		"\u000f\u0000\u000046\u0003\u0006\u0003\u000057\u0003\n\u0005\u000065\u0001"+
		"\u0000\u0000\u000078\u0001\u0000\u0000\u000086\u0001\u0000\u0000\u0000"+
		"89\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:;\u0003\b\u0004\u0000"+
		";\u0005\u0001\u0000\u0000\u0000<>\u0005\u0018\u0000\u0000=<\u0001\u0000"+
		"\u0000\u0000>?\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001"+
		"\u0000\u0000\u0000@\u0007\u0001\u0000\u0000\u0000AC\u0005\u000f\u0000"+
		"\u0000BD\u0005\u0018\u0000\u0000CB\u0001\u0000\u0000\u0000DE\u0001\u0000"+
		"\u0000\u0000EC\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000F\t\u0001"+
		"\u0000\u0000\u0000GH\u0005\u0011\u0000\u0000HI\u0005\u0002\u0000\u0000"+
		"IJ\u0005\u000f\u0000\u0000JK\u0003\f\u0006\u0000KL\u0003\u0010\b\u0000"+
		"LN\u0003\u0012\t\u0000MO\u0003\u000e\u0007\u0000NM\u0001\u0000\u0000\u0000"+
		"OP\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000"+
		"\u0000Q\u000b\u0001\u0000\u0000\u0000RT\u0005\u0018\u0000\u0000SR\u0001"+
		"\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000"+
		"UV\u0001\u0000\u0000\u0000VX\u0001\u0000\u0000\u0000WS\u0001\u0000\u0000"+
		"\u0000WX\u0001\u0000\u0000\u0000X\r\u0001\u0000\u0000\u0000YZ\u0005\u0005"+
		"\u0000\u0000Z[\u0005\u0011\u0000\u0000[\\\u0005\u0006\u0000\u0000\\]\u0005"+
		"\u0018\u0000\u0000]^\u0003\u0016\u000b\u0000^_\u0003\u0010\b\u0000_`\u0003"+
		"\u0014\n\u0000`b\u0003\u0018\f\u0000ac\u0003,\u0016\u0000ba\u0001\u0000"+
		"\u0000\u0000bc\u0001\u0000\u0000\u0000c\u000f\u0001\u0000\u0000\u0000"+
		"de\u0005\u0003\u0000\u0000ef\u0005\u0004\u0000\u0000f\u0011\u0001\u0000"+
		"\u0000\u0000gh\u0001\u0000\u0000\u0000h\u0013\u0001\u0000\u0000\u0000"+
		"ij\u0001\u0000\u0000\u0000j\u0015\u0001\u0000\u0000\u0000km\u0005\u0007"+
		"\u0000\u0000ln\u0005\u0018\u0000\u0000ml\u0001\u0000\u0000\u0000no\u0001"+
		"\u0000\u0000\u0000om\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000"+
		"pr\u0001\u0000\u0000\u0000qk\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000"+
		"\u0000r\u0017\u0001\u0000\u0000\u0000sw\u0005\b\u0000\u0000tx\u0003\u001a"+
		"\r\u0000ux\u0003\u001c\u000e\u0000vx\u0003\u001e\u000f\u0000wt\u0001\u0000"+
		"\u0000\u0000wu\u0001\u0000\u0000\u0000wv\u0001\u0000\u0000\u0000x\u0019"+
		"\u0001\u0000\u0000\u0000yz\u0005\t\u0000\u0000z{\u0003 \u0010\u0000{\u001b"+
		"\u0001\u0000\u0000\u0000|}\u0005\n\u0000\u0000}~\u0003$\u0012\u0000~\u001d"+
		"\u0001\u0000\u0000\u0000\u007f\u0080\u0005\r\u0000\u0000\u0080\u001f\u0001"+
		"\u0000\u0000\u0000\u0081\u0083\u0005\u000f\u0000\u0000\u0082\u0084\u0003"+
		"\"\u0011\u0000\u0083\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000"+
		"\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000"+
		"\u0000\u0000\u0086!\u0001\u0000\u0000\u0000\u0087\u0088\u0005\u0011\u0000"+
		"\u0000\u0088\u0089\u0005\u0018\u0000\u0000\u0089#\u0001\u0000\u0000\u0000"+
		"\u008a\u008c\u0003&\u0013\u0000\u008b\u008a\u0001\u0000\u0000\u0000\u008c"+
		"\u008d\u0001\u0000\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008d"+
		"\u008e\u0001\u0000\u0000\u0000\u008e%\u0001\u0000\u0000\u0000\u008f\u0091"+
		"\u0003(\u0014\u0000\u0090\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001"+
		"\u0000\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0092\u0093\u0001"+
		"\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094\u0095\u0003"+
		"*\u0015\u0000\u0095\'\u0001\u0000\u0000\u0000\u0096\u0097\u0005\u000b"+
		"\u0000\u0000\u0097\u009a\u0005\u000f\u0000\u0000\u0098\u009a\u0005\u000b"+
		"\u0000\u0000\u0099\u0096\u0001\u0000\u0000\u0000\u0099\u0098\u0001\u0000"+
		"\u0000\u0000\u009a)\u0001\u0000\u0000\u0000\u009b\u009c\u0005\u000b\u0000"+
		"\u0000\u009c\u009d\u0005\u000f\u0000\u0000\u009d\u00a1\u0005\f\u0000\u0000"+
		"\u009e\u009f\u0005\u000b\u0000\u0000\u009f\u00a1\u0005\f\u0000\u0000\u00a0"+
		"\u009b\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a1"+
		"+\u0001\u0000\u0000\u0000\u00a2\u00a8\u0005\u000e\u0000\u0000\u00a3\u00a5"+
		"\u0005\u0018\u0000\u0000\u00a4\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a7"+
		"\u0001\u0000\u0000\u0000\u00a7\u00a9\u0001\u0000\u0000\u0000\u00a8\u00a4"+
		"\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9-\u0001"+
		"\u0000\u0000\u0000\u00118?EPUWboqw\u0085\u008d\u0092\u0099\u00a0\u00a6"+
		"\u00a8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}