package com.learn.question.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.learn.question.model.SentenceArrangementModel;

@ExtendWith(MockitoExtension.class)
public class SentenceServiceTests {

	@InjectMocks
	private SentenceArrangementService sentenceService;
	
	@Test
	void testSimpleSuccessCase() {
		SentenceArrangementModel sentence = sentenceService.generateSentenceQuestion("This is a number");
		assertNotNull(sentence);
		System.out.println(sentence.getQuestionSentence());
	}
	
}
