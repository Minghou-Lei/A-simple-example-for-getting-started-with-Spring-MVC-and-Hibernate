package BookStore.Test;

import BookStore.dao.impl.BookStoreDaoImpl;
import BookStore.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {

	@Test
	public void saveBookTest() {
		Book b = new Book();
		b.setBookName("测试书");
		b.setAuthor("测试人员");
		b.setClassID(1);
		b.setInventory(0);
		b.setPrice(0);
		b.setPublish("测试出版社");

		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.save(b);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}

	@Test
	public void deleteBookTest() {
		Book b = new Book();
		b.setBookID(4);
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.delete(b);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}

	@Test
	public void addOrderInfo() {
		OrderInfo oi = new OrderInfo();
		OrderInfoKey oik = new OrderInfoKey();
		oik.setOrderID(5);
		oik.setBookID(1);
		oi.setQuantity(3);

		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.save(oi);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

	}

	@Test
	public void findAllBook() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Query books = session.createQuery("FROM Book ");
		List<Book> bookList = books.list();
		Query className = session.createQuery("FROM ClassInfo ");
		List<ClassInfo> classInfoList = className.list();

		Map<Integer, String> classMap = new HashMap<>();
		for (int i = 0; i < classInfoList.size(); i++) {
			classMap.put(classInfoList.get(i).getClassID(), classInfoList.get(i).getClassName());
		}

		for (int i = 0; i < bookList.size(); i++) {
			String name = classMap.get(bookList.get(i).getClassID());
			System.out.print(bookList.get(i));
			System.out.print(" -> " + name + "\n");
		}
	}

	@Test
	public void findAllUsers() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Query user = session.createQuery("FROM UserInfo");
		List<UserInfo> userInfoList = user.list();
		for (UserInfo u : userInfoList) {
			System.out.println(u.getBirthday());
		}
	}

	@Test
	public void findNothing(){
		BookStoreDaoImpl dao = new BookStoreDaoImpl();
		dao.makeConnection();
		Query bookQuery = dao.getQueryByHQL("FROM book WHERE BookID = 100");

	}

}
