package net.hunau.bookms.bean;

import java.sql.Date;

public class Feedback {
	private int feedid;//�������
	private String userNo;//ѧ��
	private Date date;//����ʱ��
	private String content;//����
	private int handle;//�Ƿ���
	
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
