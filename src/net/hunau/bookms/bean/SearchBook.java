package net.hunau.bookms.bean;


public class SearchBook {
	private String tbarcode;
	private String bookName;
	private String author;
	private String bookTypeId;
	private String location;
	private Integer amount;
	private String introduction;
	public void setIntroduction(String introduction) {
		this.introduction=introduction;
	}
	public String getIntroduction() {
		return introduction;
	}
	public  void setTbarcode(String tbarcode) {
		this.tbarcode=tbarcode;
	}
	public String getTbarcode() {
		return tbarcode;
	}
	public  void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookName() {
		return bookName;
	}
	public  void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor() {
		return author;
	}
	public  void setBookTypeId(String bookTypeId) {
		this.bookTypeId = bookTypeId;
	}
	public String getBookTypeId() {
		  return bookTypeId;
	}
	public  void setLocation(String location) {
		this.location = location;
	}
	public String getLocation() {
		   return location;
	}
	public  void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getAmount() {
		return amount;
	}
	public String toString() {
		String str = "[tbarcode:"+tbarcode+",bookName:"+bookName+
				",author:"+author+",bookTypeId:"+bookTypeId+",location:"
				+location+",amount:"+amount+",introduction:"+introduction+"]";
		return str;
	}
	
}
