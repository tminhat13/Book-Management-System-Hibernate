package com.tminhat.BMS;

import java.util.Scanner;

//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;

import com.tminhat.BMS.model.Book;
import com.tminhat.BMS.model.User;
import com.tminhat.BMS.service.BookService;
import com.tminhat.BMS.service.UserService;

public class App 
{
//	private static SessionFactory fac;
//	private static Session ses;
//	private static Transaction tx;
	static User currentUser = new User();
	private static Scanner sc;
	private UserService uservice;
	private BookService bservice;
	
	public App() {
//		fac=new Configuration().configure("hiber.cfg.xml").addAnnotatedClass(Book.class)
//				.addAnnotatedClass(User.class).buildSessionFactory();
		sc=new Scanner(System.in);
		uservice = new UserService();
		bservice = new BookService();
	}
	public int start() {
		while(currentUser.getUsername()==null) {
			try {
				System.out.println("---------Welcome---------");
				System.out.println("1. New User");
				System.out.println("2. Sign In");
				System.out.println("3. Close App");
				System.out.println("Enter select number: ");
				int input = sc.nextInt();
				sc.nextLine();
				switch(input) {
				case 1:
					currentUser = uservice.register();;
					break;
				case 2:
					currentUser = uservice.login();
					break;
				case 3:
					System.exit(0);
					break;
				default:
					System.out.println("Invalid number!");
					break;
				}
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return 1;
	}
	
	public int userMenu(App appObj) {
		Book currentBook = new Book();
		while(currentUser!=null && currentUser.getUsername()!=null) {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("---------User: " + currentUser.getUsername() + ", Total of Books:" +
						 "---------");
				System.out.println("---------Reading Book: " + 
						(currentBook.getBookName()!=null?currentBook.getBookName():"No book selected") + "---------");
				System.out.println("---------------------------------------------");
				System.out.println("1. Sign Out");
				System.out.println("2. Add a new book");
				System.out.println("3. Show all new books");
				System.out.println("4. Add the current selected book to favorite");
				System.out.println("5. Show all favorite books");
				System.out.println("6. Add the selected book to completed");
				System.out.println("7. Show all completed books");
				System.out.println("8. Select reading book by Id");
				System.out.println("9. Show current book details");
				System.out.println("10. Close App");
				System.out.println("Enter select number: ");
				int input = sc.nextInt();
				sc.nextLine();
				switch(input) {
				case 1:
					currentUser = new User();
					return 1;
				case 2:
					bservice.insertBook(App.currentUser);
					break;
				case 3:
					currentUser = uservice.updateUser(currentUser);
					currentUser.getBookList().forEach((book)->System.out.println(book));
					break;
//				case 4:
////					if(currentBook.getBookISBN()!=0) {
////						if(!bservice.addFavorite(currentUser.getUsername(), currentBook.getBookISBN()))
////							System.out.println("Add to favorite successfully!");
////						else
////							System.out.println("The book was already added!");
////					}
////					else
////						System.out.println("No book selected!");
//					break;
//				case 5:
////					bservice.getFavBooks(currentUser.getUsername()).forEach((book)->System.out.println(book));
//					break;
//				case 6:
////					if(currentBook.getBookISBN()!=0) {
////						if(!bservice.addCompleted(currentUser.getUsername(), currentBook.getBookISBN()))
////							System.out.println("Add to favorite successfully!");
////						else
////							System.out.println("The book was already added!");
////					}
////					else
////						System.out.println("No book selected!");
//					break;
//				case 7:
////					bservice.getCompletedBooks(currentUser.getUsername()).forEach((book)->System.out.println(book));
//					break;
				case 8:
					int bookId;
					System.out.println("Enter book Id: ");
					bookId = sc.nextInt();
					currentBook = bservice.getBookById(bookId);
					if(currentBook.getBookISBN()!=0) {
						System.out.println("Selected Book: " + currentBook.getBookName());
					}
					else
						System.out.println("Not found!");
					break;
				case 9:
					if(currentBook.getBookISBN()!=0) {
						System.out.println(currentBook);
					}
					else
						System.out.println("No book selected!");
					break;
				case 10:
					return 0;
				default:
					System.out.println("Invalid number!");
					break;
				}
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		return 0;
	}
	
    public static void main( String[] args )
    {
    	App appobj=new App();
    	while(true) {
			if(appobj.start()==1) {
				if(appobj.userMenu(appobj)==0) {
					break;
				}
			}
			else
				break;
		}
		sc.close();
    }
}
