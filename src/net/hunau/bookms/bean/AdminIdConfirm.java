package net.hunau.bookms.bean;

public class AdminIdConfirm {
	private String cno;//���
	private String adminId;//����Ա���
	private int available;//�ù���Ա����Ƿ�ע��,0Ϊ��ע�ᣬ�������ٴ���
	
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
