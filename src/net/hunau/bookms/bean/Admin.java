package net.hunau.bookms.bean;

public class Admin {
	private String adminId;//管理员Id
	private String username;//管理员姓名
	private String password;//管理员密码
	private String truename;//真实姓名
	private int level;//管理员级别，0为普通，1为超级管理员
	
	public void setAdminId(String adminId)
	{
		this.adminId=adminId;
	}
	public String getAdminId()
	{
		return this.adminId;
	}
	
	public void setUsername(String username)
	{
		this.username=username;
	}
	public String getUsername()
	{
		return this.username;
	}
	
	public void setTrueName(String truename)
	{
		this.truename=truename;
	}
	public String getTruename()
	{
		return this.truename;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	public String getPassword()
	{
		return this.password;
	}
	
	public void setLevel(int level)
	{
		this.level=level;
	}
	public int getLevel()
	{
		return this.level;
	}
	
	public String toString() {
		return "用户名：" + username + "\n真实姓名：" + truename+"\n编号：" + adminId+ "\n密码："+password+"\n\n";
	}
	

}
