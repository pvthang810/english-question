package com.learn.question.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learn.question.model.SentenceArrangementModel;

@Service
public class SentenceArrangementService {

	public static String ORIGINAL_SEPARATOR = " ";
	public static String QUESTION_SEPARATOR = " / ";
	private static String ORIGINAL_SENTENCE_FOLDER_PATH = "D:\\Test\\";

	public SentenceArrangementModel generateSentenceQuestion(String originalSentence) {
		StringBuilder questionSentence = new StringBuilder();

		String[] originalWords = originalSentence.split(ORIGINAL_SEPARATOR);
		Arrays.sort(originalWords);
		for (String word : originalWords) {
			questionSentence.append(word + QUESTION_SEPARATOR);
		}

		return new SentenceArrangementModel(originalSentence, questionSentence.toString());
	}

	public List<SentenceArrangementModel> generateSentenceQuestions(String fileName) throws IOException {
		List<SentenceArrangementModel> result = new ArrayList<SentenceArrangementModel>();
		Path path = Paths.get(ORIGINAL_SENTENCE_FOLDER_PATH + fileName + ".txt");
		List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);

		for (String line : allLines) {
			String[] sentences = line.split("[\\!\\?\\.]");
			for (String sentence : sentences) {
				/*
				 * char endSentenceMark = line.charAt(line.indexOf(sentence)+sentence.length());
				 * sentence = sentence.trim().concat(Character.toString(endSentenceMark));
				 */
				if (sentence.contains("\"")) continue;
				result.add(this.generateSentenceQuestion(sentence.trim()));
			}

		}
		return result;
	}
}
