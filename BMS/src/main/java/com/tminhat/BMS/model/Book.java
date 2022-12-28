package com.tminhat.BMS.model;

import javax.persistence.*;

@Entity
@Table(name="books")
public class Book {

	@Id
	private int bookISBN;
	@Column
	private String bookName;
	@Column
	private String description;
	@Column
	private String authorName;
	@ManyToOne
	@JoinColumn(name="userObj")
	private User userObj;
	

	public User getUserObj() {
		return userObj;
	}
	public void setUserObj(User userObj) {
		this.userObj = userObj;
	}
	public int getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(int bookISBN) {
		this.bookISBN = bookISBN;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	//toString method
	public String toString() {
		return "Book ID: " + this.bookISBN +", Book Name: " + this.bookName + ", Author: " + this.authorName + ", Description: " + this.description;
	}
}
