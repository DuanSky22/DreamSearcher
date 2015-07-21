package com.dream.service;

import java.util.List;

import com.dream.data.Document;

public interface Search {
	
	public double  getScore(Document doc,List<String> queryList);
	

}
