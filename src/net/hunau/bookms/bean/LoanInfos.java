package net.hunau.bookms.bean;

import java.sql.Date;

public class LoanInfos{
	private String barcode;//书籍id
	private String userNo;//用户No.
	private Date borrowDate;//借出日期
	private Date returnDate;//实际归还日期
	private Integer loaninId;
	private String bookname;
	private String truename;//真实姓名
	private Date deadline; //最大归还日期
	private Integer overtime;//超时天数
	private Float fine; //违约金
	public Integer getLaoninId() {
		return loaninId;
	}
	public void setLaoninId(Integer laoninId) {
		this.loaninId = laoninId;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBooknmae(String bookname) {
		this.bookname=bookname;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getTruename() {
		return truename;
	}
	public Date getDealine() {
		return deadline;
	}
	public void setDealine(Date dealine) {
		this.deadline = dealine;
	}
	public Integer getOvertime() {
		return overtime;
	}
	public void setOvertime(int overtime) {
		this.overtime = overtime;
	}
	public Float getFine() {
		return fine;
	}
	public void setFine(Float fine) {
		this.fine = fine;
	}
	public void setBarcode(String barcode) {
		this.barcode=barcode;
	}
	public String getBarcode() {
		return this.barcode;
	}
	public void setUserNo(String userNo) {
		this.userNo=userNo;
	}
	public String getUserNo() {
		return this.userNo;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate=borrowDate;
	}
	public Date getBorrowDate() {
		return this.borrowDate;
	}
	public Date getReturnDate() {
		return this.returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate=returnDate;
	}
}
