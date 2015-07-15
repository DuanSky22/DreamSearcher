package com.dream.service;

import java.util.List;

import com.dream.data.Document;

public class DreamControler {

	private List<Document> docList; //处理的文档集合
	private int total; //处理文档的总数
	private int current;//目前正在处理的文档
	private static DreamControler instance;
	
	private DreamControler() {}
	
	public static synchronized DreamControler getInstance(){
		if(instance==null){
			instance=new DreamControler();
		}
		return instance;
	}
	
	public List<Document> getDocList() {
		return docList;
	}
	
	public void setDocList(List<Document> docList) {
		this.docList = docList;
		if(docList!=null){
			this.total=docList.size();
			this.current=0;
		}
	}
	
	
}
