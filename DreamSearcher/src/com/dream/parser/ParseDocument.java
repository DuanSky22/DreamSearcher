package com.dream.parser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hdgf.extractor.VisioTextExtractor;
import org.apache.poi.hpbf.extractor.PublisherTextExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.xmlbeans.XmlException;

import com.dream.data.Document;

public class ParseDocument {
	
	public String parse(Document document) throws FileNotFoundException{
		if(document==null)
			return "";
		String type=document.getFileType();
		BufferedInputStream input=new BufferedInputStream(
				new FileInputStream(document.getFilePath()));
		String text="";
		switch(type){
		//Microsoft Office文件类型
		case "docx":
		case "doc":
		case "ppt":
		case "pptx":
		case "xls":	
		case "xlsx":
		case "vsd":
		case "vsdx":
		case "pub":
			POITextExtractor extractor;
			try {
				extractor = ExtractorFactory.createExtractor(input);
				text=extractor.getText();
			} catch (IOException | OpenXML4JException
					| XmlException e) {
				text="";
				e.printStackTrace();
			}
			break;
		case "pdf":
			try {
				PDFParser pdf=new PDFParser(input);
				pdf.parse();
				text=new PDFTextStripper().getText(new PDDocument(pdf.getDocument()));
			} catch (IOException e) {
				text="";
				e.printStackTrace();
			}
		}
		return text;
	}
	
	

}
