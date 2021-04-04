package net.hunau.bookms.bean;

import java.sql.Date;

public class Feedback {
	private int feedid;//反馈编号
	private String userNo;//学号
	private Date date;//反馈时间
	private String content;//内容
	private int handle;//是否处理
	
	public void setFeedid(int feedid)
	{
		this.feedid=feedid;
	}
	public int getFeedid()
	{
		return this.feedid;
	}
	public void setUserNo(String userNo)
	{
		this.userNo=userNo;
	}
	public String getUserNo()
	{
		return this.userNo;
	}
	
	public void setDate(Date date)
	{
		this.date=date;
	}
	public Date getDate()
	{
		return this.date;
	}
	
	public void setContent(String content)
	{
		this.content=content;
	}
	public String getContent()
	{
		return this.content;
	}
	
	public void setHandle(int handle)
	{
		this.handle=handle;
	}
	public int getHandle()
	{
		return this.handle;
	}
	
}
