package net.hunau.bookms.bean;

public class User {
	private String userNo;//学号
	private String username;//用户名
	private String truename;//真实姓名
	private String password;//密码
	private String readerType;//读者类型
	private String sex;//性别
	private String telephone;//电话
	private String email;//Email
	private String QQ;
	private String address;//地址
	private String birthday;//生日
	private float balance;//余额
	private int validity;//是否冻结
	
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
		return "用户名：" + username + "\n密码：" + password+ "\n学号："+userNo+ "\n真实姓名：" + truename+"\n类型："+readerType+ "\n性别："+sex
				+ "\n手机：" + telephone +"\nEmail:" +email+ "\nQQ:" + QQ+"\n地址:" + address+"\n生日："+birthday
				+"\n余额："+balance+"\n有效性："+validity+"\n\n";
	}
	
}
