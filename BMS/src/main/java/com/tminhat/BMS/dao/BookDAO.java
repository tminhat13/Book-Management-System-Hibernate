package com.tminhat.BMS.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tminhat.BMS.model.Book;
import com.tminhat.BMS.model.User;

public class BookDAO {

	private SessionFactory fact;
	private Session ses;
	private Transaction tx;
	public BookDAO() {
		fact =new Configuration().configure("hiber.cfg.xml").addAnnotatedClass(Book.class)
				.addAnnotatedClass(User.class).buildSessionFactory();
	}
	public void insertData(Book b1) {
		try {
			ses=fact.openSession();
			tx=ses.beginTransaction();
			ses.save(b1);
			tx.commit();
			System.out.println("Data saved!");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
