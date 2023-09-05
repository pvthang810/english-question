package com.learn.question.model;

public class SentenceArrangementModel {
	private String answerSentence;
	private String questionSentence;
	private String responseSentence;
	public static int NUMBER_OF_BLANK_LINE = 2;
	
	public SentenceArrangementModel() {}
	public SentenceArrangementModel(String answerSentence, String questionSentence) {
		this.answerSentence = answerSentence;
		this.questionSentence = questionSentence;
	}
	
	public String getAnswerSentence() {
		return answerSentence;
	}
	public void setAnswerSentence(String answerSentence) {
		this.answerSentence = answerSentence;
	}
	public String getQuestionSentence() {
		return questionSentence;
	}
	public void setQuestionSentence(String questionSentence) {
		this.questionSentence = questionSentence;
	}
	public String getResponseSentence() {
		return responseSentence;
	}
	public void setResponseSentence(String responseSentence) {
		this.responseSentence = responseSentence;
	}
	
		
	
	
}
