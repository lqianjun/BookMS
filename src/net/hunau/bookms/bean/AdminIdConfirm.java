package net.hunau.bookms.bean;

public class AdminIdConfirm {
	private String cno;//编号
	private String adminId;//管理员编号
	private int available;//该管理员编号是否注册,0为已注册，不可以再次用
	
	public void setCno(String cno)
	{
		this.cno=cno;
	}
	public String getCno()
	{
		return this.cno;
	}
	
	public void setAdminId(String adminId)
	{
		this.adminId=adminId;
	}
	public String getAdminId()
	{
		return this.adminId;
	}
	
	public void setAvailable(int available)
	{
		this.available=available;
	}
	public int getAvailable()
	{
		return this.available;
	}
}
