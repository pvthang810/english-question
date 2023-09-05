package com.learn.question.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import com.learn.question.model.SentenceArrangementModel;

@Service
public class TestExportService {
	public static SimpleDateFormat FILE_NAME_DATE_FORMAT = new SimpleDateFormat("YYMMddHHmmss");

	public byte[]  exportToMSWords(List<SentenceArrangementModel> sentences) throws IOException {
		ByteArrayOutputStream  out = new ByteArrayOutputStream();
		
		try (XWPFDocument exportFile = new XWPFDocument()) {

            XWPFParagraph paragraph = exportFile.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.LEFT);

            XWPFRun runner = paragraph.createRun();
            runner.setBold(false);
            runner.setItalic(false);
            runner.setFontSize(14);
            runner.setFontFamily("New Roman");
            
            int questionNumber = 1;
            for (SentenceArrangementModel sentence : sentences) {
            	runner.setText(questionNumber + ". "+sentence.getQuestionSentence());
            	runner.addBreak();
            	for (int i=0; i<SentenceArrangementModel.NUMBER_OF_BLANK_LINE; i++) {
            		runner.setText("Answer:................................................................................................................");
            		runner.addBreak();
            	}
            	runner.addBreak();
            	questionNumber++;
			}

            exportFile.write(out);
            out.close();
            exportFile.close();
            
            return out.toByteArray();
        } catch (IOException ex) {
        	throw ex;
        }
	}
}
