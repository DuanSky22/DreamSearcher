package com.dream.service;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dream.data.Document;
import com.dream.parser.ParseDocument;

public class SearchTask implements Runnable,Search{
	
	List<Document> docList; //搜索的文件列表
	Map<Document,Double> result=new HashMap<Document, Double>(); //文档得分
	List<String> queryList;//查询关键词列表
	ParseDocument parseDoc=new ParseDocument();

	public SearchTask(List<Document> docList,List<String> queryList) {
		this.docList=docList;
		this.queryList=queryList;
	}
	@Override
	public void run() {
		if(docList==null || docList.isEmpty())
			return;
		for(Document document : docList){
			double score=getScore(document,queryList);
			result.put(document, score);
		}
	}
	public Map<Document,Double> getDocScore(){
		return result;
	}

	@Override
	public double getScore(Document doc, List<String> queryList) {
		
		if(queryList==null || queryList.isEmpty())
			return 0;
		String text="";
		try {
			text=parseDoc.parse(doc);
		} catch (FileNotFoundException e) {
			System.out.println(doc.getFilePath()+" 文档解析出错！");
			e.printStackTrace();
		}	
		
		if(text==null || text.length()==0)
			return 0;
		double result=0;
		for(String query : queryList){
			result+=getRepeatTime(text,query);
		}
		return result;
	}
	
	private int getRepeatTime(String text,String query){
		int result=0;
		if(text==null || text.length()==0 || query==null || query.length()==0)
			return 0;
		int index=text.indexOf(query);
		if(index==-1)
			return 0;
		while(index!=-1){
			result++;
			index=text.indexOf(query, index+1);
		}
		return result;
	}

}
