package net.hunau.bookms.bean;

public class User {
	private String userNo;//ѧ��
	private String username;//�û���
	private String truename;//��ʵ����
	private String password;//����
	private String readerType;//��������
	private String sex;//�Ա�
	private String telephone;//�绰
	private String email;//Email
	private String QQ;
	private String address;//��ַ
	private String birthday;//����
	private float balance;//���
	private int validity;//�Ƿ񶳽�
	
	public void setUserNo(String userNo)
	{
		this.userNo=userNo;
	}
	
	public String getUserNo()
	{
		return this.userNo;
	}
	
	public void setUsername(String username)
	{
		this.username=username;
	}
	public String getUsername()
	{
		return this.username;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	public String getPassword()
	{
		return this.password;
	}
	
	public void setReaderType(String readerType)
	{
		this.readerType=readerType;
	}
	public String getReaderType()
	{
		return this.readerType;
	}
	
	public void setSex(String sex)
	{
		this.sex=sex;
	}
	public String getSex()
	{
		return this.sex;
	}
	
	public void setTelePhone(String telephone)
	{
		this.telephone=telephone;
	}
	public String getTelePhone()
	{
		return this.telephone;
	}
	
	public void setEmail(String email)
	{
		this.email=email;
	}
	public String getEmail()
	{
		return this.email;
	}
	
	public void setQQ(String QQ)
	{
		this.QQ=QQ;
	}
	public String getQQ()
	{
		return this.QQ;
	}
	
	public void setAddress(String address)
	{
		this.address=address;
	}
	public String getAddress()
	{
		return this.address;
	}
	
	public void setBirthday(String birthday)
	{
		this.birthday=birthday;
	}
	public String getBirthday()
	{
		return this.birthday;
	}
	
	public void setBalance(float balance)
	{
		this.balance=balance;
	}
	public float getBalance()
	{
		return this.balance;
	}
	public void setValidity(int validity)
	{
		this.validity=validity;
	}
	public int getValidity()
	{
		return this.validity;
	}
	
	public void setTruename(String truename)
	{
		this.truename = truename;
	}
	public String getTruename()
	{
		return this.truename;
	}
	
	public String toString() {
		return "�û�����" + username + "\n���룺" + password+ "\nѧ�ţ�"+userNo+ "\n��ʵ������" + truename+"\n���ͣ�"+readerType+ "\n�Ա�"+sex
				+ "\n�ֻ���" + telephone +"\nEmail:" +email+ "\nQQ:" + QQ+"\n��ַ:" + address+"\n���գ�"+birthday
				+"\n��"+balance+"\n��Ч�ԣ�"+validity+"\n\n";
	}
	
}
