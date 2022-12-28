package com.tminhat.BMS.dao;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tminhat.BMS.model.Book;
import com.tminhat.BMS.model.User;

public class UserDAO {

	private SessionFactory fact;
	private Session ses;
	private Transaction tx;
	public UserDAO() {
		fact=new Configuration().configure("hiber.cfg.xml").addAnnotatedClass(Book.class)
				.addAnnotatedClass(User.class).buildSessionFactory();
	}
	public void insertData(User u1) {
		try {
			ses=fact.openSession();
			tx=ses.beginTransaction();
			ses.save(u1);
			tx.commit();
			System.out.println("Data saved!");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public User isValidated(User user) {
		ses=fact.openSession();
		String hql = "from User u where u.username=?1 and u.password=?2";
		Query<User> query=ses.createQuery(hql);
				query.setParameter(1, user.getUsername());
				query.setParameter(2, user.getPassword());
		List<User> userList=query.list();
		if(userList.size()>0)
		{
			return userList.get(0);
		}
		return null;
	}
	
	public User update(User user) {
		ses=fact.openSession();
		return ses.find(User.class, user.getUserId());
	}
}
