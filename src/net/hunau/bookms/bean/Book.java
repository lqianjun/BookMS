package net.hunau.bookms.bean;

import java.util.Date;

public class Book {
	private String barcode; //�鼮��ţ�������ţ�
	private String tbarcode;//һ�����
	private String bookName; //����
	private String author; //����
	private String bookTypeId;//�鼮����id
	private String location;//�鼮��ŵص�
	private int lend; //�Ƿ���
	private float price;//����
	private int damage;//�𻵳̶�
	private String publish;//������
	private Date publishDate;//��������
	private String introduction;//���
	
	
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
		return "���룺" + barcode + "\n���ƣ�" + bookName+ "\n���ߣ�"+author+ "\n���ͣ�"+bookTypeId+ "\nλ�ã�"+location
				+ "\n�Ƿ�����" + lend +"\n����:" +price+ "\n������:" + publish+"\n��������:" + publishDate
				+"\n��飺"+introduction;
				
	}
}
