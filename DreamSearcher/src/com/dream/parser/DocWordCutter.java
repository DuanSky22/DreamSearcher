package com.dream.parser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hwpf.extractor.WordExtractor;

import com.lingjoin.divideWords.NlpirUtils;


public class DocWordCutter extends WordCutter {
	
	public static void main(String args[]){
		String url="D:\\test.doc";
		FileInputStream input;
		try {
			input = new FileInputStream(new File(url));
			WordExtractor word = new WordExtractor(new BufferedInputStream(input));
			String text=word.getText().replace(" ", "").replace("\n", "");
			System.out.println(text);
			NlpirUtils.Nlpir_init();
			String data = NlpirUtils.NLPIR_ParagraphProcess(text, 1);
			String cut[]=data.split(" ");
			System.out.println(data);
			System.out.println(NlpirUtils.NLPIR_Exit());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	String[] getWordList(String url) throws IOException {
		FileInputStream input=new FileInputStream(new File(url));
		WordExtractor word=new WordExtractor(new BufferedInputStream(input));
		String text=word.getText();
		String data = NlpirUtils.NLPIR_ParagraphProcess(text, 1);
		System.out.println(data);
		System.out.println(NlpirUtils.NLPIR_Exit());
		return null;
	}

}
