package net.hunau.bookms.bean;

import java.util.Date;

public class Book {
	private String barcode; //书籍编号（二级编号）
	private String tbarcode;//一级编号
	private String bookName; //书名
	private String author; //作者
	private String bookTypeId;//书籍类型id
	private String location;//书籍存放地点
	private int lend; //是否借出
	private float price;//单价
	private int damage;//损坏程度
	private String publish;//出版社
	private Date publishDate;//出版日期
	private String introduction;//简介
	
	
	public void setTbarcode(String tbarcode) {
		this.tbarcode=tbarcode;
	}
	public String getTbarcode() {
		return this.tbarcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode=barcode;
	}
	public String getBarcode() {
		return this.barcode;
	}
	public void setBookName(String bookName) {
		this.bookName=bookName;
	}
	
	public String getBookName() {
		return this.bookName;
	}
	public void setAuthor(String author) {
		this.author=author;
	}
	public String getAuthor() {
		return this.author;
	}
	public void setBookTypeId(String bookTypeId) {
		this.bookTypeId=bookTypeId;
	}
	public String getBookTypeId() {
		return this.bookTypeId;
	}
	public void setLocation(String location) {
		this.location=location;
	}
	public String getLocation() {
		return this.location;
	}
	public void setLend(int lend) {
		this.lend=lend;
	}
	public int getLend() {
		return this.lend;
	}
	public void setPrice(float price) {
		this.price=price;
	}
	public float getPrice() {
		return this.price;
	}
	public void setPublish(String publish) {
		this.publish=publish;
	}
	public String getPublish() {
		return this.publish;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate=publishDate;
	}
	public Date getPublishDate() {
		return this.publishDate;
	}
	public void setIntroduction(String introduction) {
		this.introduction=introduction;
	}
	public String getIntroduction() {
		return this.introduction;
	}
	
	public void setDamage(int damage)
	{
		this.damage=damage;
	}
	public int getDamage()
	{
		return this.damage;
	}
	public String toString() {
		return "条码：" + barcode + "\n名称：" + bookName+ "\n作者："+author+ "\n类型："+bookTypeId+ "\n位置："+location
				+ "\n是否借出：" + lend +"\n单价:" +price+ "\n出版社:" + publish+"\n出版日期:" + publishDate
				+"\n简介："+introduction;
				
	}
}
