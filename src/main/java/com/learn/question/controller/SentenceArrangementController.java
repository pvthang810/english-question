package com.learn.question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learn.question.model.SentenceArrangementModel;
import com.learn.question.service.SentenceArrangementService;
import com.learn.question.service.TestExportService;

@Controller
public class SentenceArrangementController {

	@Autowired
	private SentenceArrangementService sentenceService;

	@Autowired
	private TestExportService exportService;

	@GetMapping("/sentence/")
	public ResponseEntity<String> getQuestionSentence() {
		SentenceArrangementModel sentence = sentenceService.generateSentenceQuestion("This is a number");
		if (sentence == null) {
			return new ResponseEntity<String>("Some problem", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>(sentence.getQuestionSentence(), HttpStatus.OK);
		}
	}

	@GetMapping("/getSentenceArrange")
	public ResponseEntity<?> generateSentenceArrangementTest(@RequestParam("fileName") String fileName) {
		try {
			List<SentenceArrangementModel> sentences = sentenceService.generateSentenceQuestions(fileName);
			String headerValue = "attachment; filename=\"" + fileName + ".docx" + "\"";
			
			ResponseEntity<byte[]> responseEntity = ResponseEntity.ok()
					.contentType(MediaType.parseMediaType("application/msword"))
					.header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
					.body(exportService.exportToMSWords(sentences));
			
			return responseEntity;

		} catch (Exception ex) {
			final HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			ex.printStackTrace();
			return new ResponseEntity<String>("{\"test\":"+ex.getMessage(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
