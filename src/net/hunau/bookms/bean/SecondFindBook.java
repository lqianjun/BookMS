package net.hunau.bookms.bean;

import java.sql.Date;

public class SecondFindBook {
		private String barcode;
		private String bookName;
		private String author;
		private Integer lend;
		private Float price;
		private String publish;
		private Date publishDate;
		private Integer damage;
		public Integer getDamage() {
			return damage;
		}
		public void setDamage(Integer damage) {
			this.damage=damage;
		}
		public String getBookName() {
			return bookName;
		}
		public void setBookName(String bookName) {
			this.bookName=bookName;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author=author;
		}
		public String getBarcode() {
			return barcode;
		}
		public void setBarcode(String barcode) {
			this.barcode = barcode;
		}
		public Integer getLend() {
			return lend;
		}
		public void setLend(Integer lend) {
			this.lend = lend;
		}
		public void setPrice(Float price) {
			this.price= price;
		}
		public Float getPrice() {
			return price;
		}
		public void setPublish(String publish) {
			this.publish = publish;
		}
		public String getPublish() {
			return this.publish;
		}
		public Date getPulishDate() {
			return this.publishDate;
		}
		public void setPublishDate(Date publishDate) {
			this.publishDate=publishDate;
		}
}
