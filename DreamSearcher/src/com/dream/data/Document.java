package com.dream.data;

import java.io.Serializable;

public class Document implements Serializable{
	
	protected long id;
	protected String fileName;
	protected String filePath;
	protected String fileType;
	protected String fileTime;
	
	public static final String DOCX="docx";
	public static final String PDF="pdf";
	public static final String TXT="txt";
	
	public Document(){}
	
	public Document(long id,String fileName,String filePath,String fileType,String fileTime){
		this.id=id;
		this.fileName=fileName;
		this.filePath=filePath;
		this.fileType=fileType;
		this.fileTime=fileTime;
	}
	

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileTime() {
		return fileTime;
	}

	public void setFileTime(String fileTime) {
		this.fileTime = fileTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

}
