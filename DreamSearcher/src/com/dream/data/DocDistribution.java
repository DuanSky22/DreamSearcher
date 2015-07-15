package com.dream.data;

public class DocDistribution {

	private long id; //文档Id
	private long position; //在文档中的位置
	private int count;//文档中出现的次数
	
	public DocDistribution(){}
	
	public DocDistribution(long id,long position,int count){
		this.id=id;
		this.position=position;
		this.count=count;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPosition() {
		return position;
	}
	public void setPosition(long position) {
		this.position = position;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
