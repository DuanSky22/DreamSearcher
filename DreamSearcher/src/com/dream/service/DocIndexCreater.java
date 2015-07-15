package com.dream.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.dream.data.Document;

public class DocIndexCreater {
	

	protected String url;
	protected LinkedList<File> fileQueue=new LinkedList<File>(); //文件处理队列
	protected int id=0;
	protected List<Document> documentList=new ArrayList<Document>(); //存储读取的文档列表
	public DocIndexCreater() {
		
	}
	
	public DocIndexCreater(String url){
		this.url=url;
	}
	
	public List<Document> getDocumentList(){
		File file=new File(url);
		if(!file.exists())
			throw new IllegalArgumentException("输入的文件路径出错，初始化失败！");
		if(file.isFile()){
			documentList.add(getDocument(file));
			return documentList;
		}
		File[] fileList=file.listFiles();
		fileQueue.addAll(Arrays.asList(fileList));
		while(!fileQueue.isEmpty()){
			File temp=fileQueue.pop();
			if(temp.isFile()){//如果是文件，则直接将这个文件添加到结果列表中
				documentList.add(getDocument(temp));
			}
			else{//如果不是文件，则将其加入到文件夹队列中
				fileQueue.addAll(Arrays.asList(temp.listFiles()));
			}
		}
		return documentList;
	}
	
	private Document getDocument(File file){
		id++;
		Document document=new Document();
		String fileName=file.getName();
		int index=fileName.lastIndexOf(".");
		document.setId(id);
		if(index!=-1){
			document.setFileName(fileName.substring(0,index));
			document.setFileType(fileName.substring(index+1));
		}
		else{
			document.setFileName(fileName);
			document.setFileType("None");
		}
		document.setFilePath(file.getAbsolutePath());
		document.setFileTime(new Timestamp(file.lastModified()).toString());
		return document;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getId() {
		return id;
	}
}
