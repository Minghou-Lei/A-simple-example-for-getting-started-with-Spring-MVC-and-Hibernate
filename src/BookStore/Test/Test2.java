package BookStore.Test;

import BookStore.dao.impl.BookStoreDaoImpl;
import BookStore.service.impl.BookStoreServiceImpl;
import BookStore.service.impl.UserServiceImpl;

import java.sql.Date;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		BookStoreDaoImpl dao = new BookStoreDaoImpl();
		dao.makeConnection();

		/*
		dao.printBooks();
		System.out.println("Please enter your username and password:");
		while (!dao.login(scanner.nextLine(),scanner.nextLine())){
			System.out.println("UserName or Password error!");
		}
		dao.close();
		*/

		BookStoreServiceImpl bookStoreService = new BookStoreServiceImpl("Minghao", "123");
		//bookStoreService.buyBook(2, 8);
		bookStoreService.printBooks();
		bookStoreService.printOrderInfo();
		UserServiceImpl userService = new UserServiceImpl();
		userService.register("TestMan", "123", null, Date.valueOf("1999-08-08"), null);
		userService.deleteUser();
	}
}
