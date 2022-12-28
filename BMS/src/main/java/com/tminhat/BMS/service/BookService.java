package com.tminhat.BMS.service;

import java.util.Scanner;

import com.tminhat.BMS.dao.BookDAO;
import com.tminhat.BMS.model.Book;
import com.tminhat.BMS.model.User;

public class BookService {

	BookDAO bookDao;
	Scanner sc;
	public BookService() {
		bookDao = new BookDAO();
		sc = new Scanner(System.in);
	}
	
	public void insertBook(User user) {
		try {
		Book b1 = new Book();
		System.out.print("Enter book ISBN: ");
		b1.setBookISBN(sc.nextInt());
		System.out.println("Enter book name ");
		b1.setBookName(sc.next());
		System.out.println("Enter Author name ");
		b1.setAuthorName(sc.next());
		System.out.println("Enter Description ");
		b1.setDescription(sc.next());
		b1.setUserObj(user);
		bookDao.insertData(b1);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public Book getBookById(int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
