package net.hunau.bookms.bean;

public class Admin {
	private String adminId;//����ԱId
	private String username;//����Ա����
	private String password;//����Ա����
	private String truename;//��ʵ����
	private int level;//����Ա����0Ϊ��ͨ��1Ϊ��������Ա
	
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
		return "�û�����" + username + "\n��ʵ������" + truename+"\n��ţ�" + adminId+ "\n���룺"+password+"\n\n";
	}
	

}
