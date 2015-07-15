package com.dream.data;

import java.util.ArrayList;
import java.util.List;

public class Word {

	private String word; //单词
	private int df=0; //文档频率
	private List<DocDistribution> docList=new ArrayList<DocDistribution>();
	
	public Word(){}
	public Word(String word){
		this.word=word;
	}
	
	//增加一个文档信息
	public void addDocDistribution(DocDistribution docDistribution){
		df++;
		docList.add(docDistribution);
	}
	//将另外一个word和并到这个文件中
	public boolean mergeWord(Word word){
		if(word==null || !word.word.equals(this.word))
			return false;
		List<DocDistribution> list=word.getDocList();
		if(list!=null){
			for(int i=0;i<list.size();i++){
				this.docList.add(list.get(i));
				df++;
			}
		}
		return true;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getDf() {
		return df;
	}
	public void setDf(int df) {
		this.df = df;
	}
	public List<DocDistribution> getDocList() {
		return docList;
	}
}
