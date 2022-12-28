package com.tminhat.BMS.service;

import java.util.Scanner;

import com.tminhat.BMS.dao.UserDAO;
import com.tminhat.BMS.model.User;

public class UserService {

	UserDAO userDao;
	private Scanner sc;
	public UserService() {
		userDao = new UserDAO();
		sc = new Scanner(System.in);
	}
	public User register() {
		User u1 = new User();
		System.out.print("Enter username: ");
		u1.setUsername(sc.nextLine());
		System.out.print("Enter password: ");
		u1.setPassword(sc.nextLine());
		userDao.insertData(u1);
		return u1;
	}
	public User login() {
		User u1 = new User();
		while(true) {
			System.out.print("Enter username: ");
			u1.setUsername(sc.nextLine());
			System.out.print("Enter password: ");
			u1.setPassword(sc.nextLine());
			u1 = userDao.isValidated(u1);
			if(u1!=null) {
				System.out.println("Login successfully!");
				return u1;
			}
			else {
				System.out.println("Invalid username or password!");
				u1 = new User();
			}
		}
	}
	
	public User updateUser(User user) {

		User findUser = userDao.update(user);
		if(findUser!=null) {
			return findUser;
		}
		else {
			return new User();
		}
	}
}
