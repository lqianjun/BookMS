package net.hunau.bookms.bean;

public class BookType {
	private String bookTypeId;//书籍类型id
	private String bookTypeName;//书籍类型名称
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
