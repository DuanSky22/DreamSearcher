package com.dream.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.dream.data.Document;

public class DreamControler {

	private List<Document> docList; //处理的文档集合
	private List<String> queryList;
	
	private int total; //处理文档的总数
	private int threadNum=4; //处理线程数目
	private static DreamControler instance;
	private Map<Document,Double> finalResult;

	
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
			if(total<4)
				threadNum=1;
			else if(total<=8)
				threadNum=2;
			else if(total<=12)
				threadNum=3;
			else
				threadNum=4;
		}
	}
	
	public void run(){
		ArrayList<ArrayList<Document>> list=divideDocList();
		ExecutorService executorService=Executors.newCachedThreadPool();
		finalResult=new HashMap<Document,Double>();
		switch(threadNum){
		case 1:
			SearchTask task=new SearchTask(docList, queryList);
			executorService.execute(task);
			executorService.shutdown();
			while(!executorService.isTerminated()){}
			finalResult=task.getDocScore();
			break;
		case 2:
			SearchTask task21=new SearchTask(list.get(0), queryList);
			SearchTask task22=new SearchTask(list.get(1), queryList);
			executorService.execute(task21);
			executorService.execute(task22);
			executorService.shutdown();
			while(!executorService.isTerminated()){}
			finalResult=task21.getDocScore();
			finalResult.putAll(task22.getDocScore());
			break;
		case 3:
			SearchTask task31=new SearchTask(list.get(0), queryList);
			SearchTask task32=new SearchTask(list.get(1), queryList);
			SearchTask task33=new SearchTask(list.get(2), queryList);
			executorService.execute(task31);
			executorService.execute(task32);
			executorService.execute(task33);
			executorService.shutdown();
			while(!executorService.isTerminated()){}
			finalResult=task31.getDocScore();
			finalResult.putAll(task32.getDocScore());
			finalResult.putAll(task33.getDocScore());
			break;
		case 4:
			SearchTask task41=new SearchTask(list.get(0), queryList);
			SearchTask task42=new SearchTask(list.get(1), queryList);
			SearchTask task43=new SearchTask(list.get(2), queryList);
			SearchTask task44=new SearchTask(list.get(3), queryList);
			executorService.execute(task41);
			executorService.execute(task42);
			executorService.execute(task43);
			executorService.execute(task44);
			executorService.shutdown();
			while(!executorService.isTerminated()){}
			finalResult=task41.getDocScore();
			finalResult.putAll(task42.getDocScore());
			finalResult.putAll(task43.getDocScore());
			finalResult.putAll(task44.getDocScore());
			break;
		default:
			break;
		}
		
	}
	
	//将原始的文档集进行分割
	private ArrayList<ArrayList<Document>> divideDocList(){
		ArrayList<ArrayList<Document>> list=new ArrayList<ArrayList<Document>>();
		int size=total/threadNum; //每个线程至少需要处理的数目
		for(int i=0;i<threadNum;i++){
			ArrayList<Document> taskDocList=new ArrayList<Document>();
			int position=i*size;
			for(int j=0;j<size;j++){
				taskDocList.add(docList.get(position));
				position++;
			}
			list.add(taskDocList);
		}
		int left=total-threadNum*size;
		if(left>0){
			for(int i=0;i<left;i++){
				int index=threadNum*size;
				list.get(i).add(docList.get(index));
				index++;
			}
		}
		return list;
	}



	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public List<String> getQueryList() {
		return queryList;
	}

	public void setQueryList(List<String> queryList) {
		this.queryList = queryList;
	}

	public List<Document> getResult(){
		List<Document> resultList=new LinkedList<Document>();
		for(Entry<Document,Double> entry : finalResult.entrySet()){
			if(entry.getValue()!=0){
				if(resultList==null || resultList.isEmpty())
					resultList.add(entry.getKey());
				else{
					Document document=entry.getKey();
					double value=entry.getValue();
					int insert=resultList.size()-1;//从最后一个开始插入
					while(insert>=0 && value>finalResult.get(resultList.get(insert))){
						insert--;
					}
					resultList.add(insert+1, document);
				}
			}
		}
		return resultList;
	}
	
	
}
