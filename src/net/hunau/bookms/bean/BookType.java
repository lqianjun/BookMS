package net.hunau.bookms.bean;

public class BookType {
	private String bookTypeId;//�鼮����id
	private String bookTypeName;//�鼮��������
	public void setBookTypeId(String bookTypeId) {
		this.bookTypeId=bookTypeId;
	}
	public String getBookType() {
		return this.bookTypeId;
	}
	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName=bookTypeName;
	}
	public String getBookName() {
		return bookTypeName;
	}
}
