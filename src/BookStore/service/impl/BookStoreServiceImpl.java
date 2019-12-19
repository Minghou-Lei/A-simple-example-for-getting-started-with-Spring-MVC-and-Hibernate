package BookStore.service.impl;

import BookStore.dao.impl.BookStoreDaoImpl;
import BookStore.domain.Book;
import BookStore.domain.ClassInfo;
import BookStore.domain.Order;
import BookStore.domain.OrderInfo;
import BookStore.service.BookStoreService;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookStoreServiceImpl implements BookStoreService, Serializable {

	//private String user;

	//以谁的身份操作数据库
	private int userID;
	//持久化操作数据库
	private BookStoreDaoImpl dao;

	public BookStoreServiceImpl(){}

	public BookStoreServiceImpl(String name, String password) {
		BookStoreDaoImpl dao = new BookStoreDaoImpl();
		dao.makeConnection();
		this.userID = dao.login(name, password);
		if (userID == -1) {
			//密码错误掷出异常
			System.out.println("Username or password error");
			throw new RuntimeException("Username or password error");
		} else
			this.dao = dao;

	}

	int getUserID() {
		return userID;
	}

	@Override
	public String printBooks() {
		StringBuilder sb = new StringBuilder();
		Query bookQuery = dao.getQueryByHQL("FROM Book");
		List<Book> bookList = bookQuery.list();
		Query className = dao.getQueryByHQL("FROM ClassInfo");
		List<ClassInfo> classInfoList = className.list();

		Map<Integer, String> classMap = new HashMap<>();
		for (int i = 0; i < classInfoList.size(); i++) {
			classMap.put(classInfoList.get(i).getClassID(), classInfoList.get(i).getClassName());
		}

		String classNameStr;
		String endStr = "";
		for (int i = 0; i < bookList.size(); i++) {
			String name = classMap.get(bookList.get(i).getClassID());

			classNameStr = "∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨" + name + "∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨∨\n\n";
			for (int j = 0; j < classNameStr.length() - 1; j++) {
				endStr += ("∧");
			}
			System.out.print(classNameStr);
			sb.append(classNameStr + "<br>");
			System.out.print(bookList.get(i) + "\n\n");
			sb.append(bookList.get(i)).append("<br>");
			System.out.println(endStr + "\n");
			sb.append(endStr).append("<br><br>");
			endStr = "";
		}
		return String.valueOf(sb);
	}

	public List<Book> getBookList(){
		Query bookQuery = dao.getQueryByHQL("FROM Book");
		List<Book> bookList = bookQuery.list();
		return bookList;
	}

	@Override
	public void buyBook(int bookID, int num) {
		Query book = dao.getQueryByHQL("FROM Book WHERE BookID =" + bookID);
		if (book == null) {
			System.out.println("No such book where bookID = " + bookID + "!");
			return;
		}
		Book buyingBook;
		try {
			buyingBook = (Book) book.list().get(0);
		} catch (Exception e) {
			System.out.println("No such book where BookID = " + bookID + "!");
			throw new RuntimeException("No such book where BookID = " + bookID + "!");
		}
		int inventory = buyingBook.getInventory();
		float price = buyingBook.getPrice();

		//若库存少于购买量则不允许购买
		if (inventory < num) {
			System.out.println("Run out of book where bookID =" + bookID + "!");
			throw new RuntimeException("Run out of book where bookID =" + bookID + "!");
		}
		int bookleft = inventory - num;
		System.out.println("BookLeft: " + bookleft);

		//更新Book表使库存减少
		Book boughtBook = new Book(buyingBook);
		boughtBook.setInventory(bookleft);
		dao.update(boughtBook);

		//创建新Order记录
		Order newOrder = new Order();
		newOrder.setUserID(userID);
		java.util.Date now = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		newOrder.setOrderDate(now);
		newOrder.setAmount(num * price);
		newOrder.setState("Shipping");
		int orderID = dao.insert(newOrder);

		//创建OrderInfo
		OrderInfo newInfo = new OrderInfo();

		//BookID 与 OrderID 是联合主键
		newInfo.setBookID(bookID);
		newInfo.setOrderID(orderID);

		newInfo.setQuantity(num);

		dao.insertWithoutReturn(newInfo);
	}

	@Override
	public void printOrderInfo() {
		Query orderQuery = dao.getQueryByHQL("FROM Order WHERE UserID = " + userID);
		List<Order> orderList = orderQuery.list();

		for (Order o : orderList) {
			System.out.println("===================================");
			System.out.println(o);
			System.out.println("===================================\n");
		}

		List<Integer> orderIDList = new ArrayList<>();
		for (Order order : orderList) {
			orderIDList.add(order.getOrderID());
		}


		//   BookID   QTY
		Map<Integer, Integer> bookQTY = new HashMap<>();
		for (int oid : orderIDList) {
			Query orderInfoQuery = dao.getQueryByHQL("FROM OrderInfo WHERE OrderID = " + oid);
			List<OrderInfo> orderInfoList = orderInfoQuery.list();
			for (OrderInfo oif : orderInfoList) {

				if (bookQTY.get(oif.getBookID()) != null) {
					bookQTY.put(oif.getBookID(), (oif.getQuantity() + bookQTY.get(oif.getBookID())));
				} else
					bookQTY.put(oif.getBookID(), oif.getQuantity());
			}
		}

		Query bookQuery = dao.getQueryByHQL("FROM Book");
		List<Book> bookList = bookQuery.list();
		Map<Integer, String> bookID = new HashMap<>();
		for (Book b : bookList) {
			bookID.put(b.getBookID(), b.getBookName());
		}
		for (Integer key : bookQTY.keySet()) {
			System.out.println(bookQTY.get(key) + " <- " + bookID.get(key));
		}

	}

	@Override
	public void cancelOrder(int OrderID) {
		Query orderQuery = dao.getQueryByHQL("FROM Order WHERE OrderID = " + OrderID);
		Query orderInfoQuery = dao.getQueryByHQL("FROM OrderInfo WHERE OrderID = " + OrderID);
		if (orderQuery.list().size() <= 0) {
			System.out.println("No such Order where OrderID =" + OrderID);
			throw new RuntimeException("No such Order where OrderID =" + OrderID);
		}
		Order thisOrder = (Order) orderQuery.list().get(0);
		if (!thisOrder.getState().equals("Shipping")) {
			System.out.println("Order is not on Shipping, Cancel not available!");
			throw new RuntimeException("Order is not on Shipping, Cancel not available!");
		}
		List<OrderInfo> orderInfoList = orderInfoQuery.list();
		for (OrderInfo oif : orderInfoList) {
			dao.delete(oif);
		}
		dao.delete(thisOrder);
	}

	public List<Order> getOrderList(){
		Query orderQuery = dao.getQueryByHQL("FROM Order WHERE UserID = "+userID);
		return (List<Order>)orderQuery.list();
	}

	public List<OrderInfo> getOrderInfoList(){
		List<Order> orderList = getOrderList();
		List<OrderInfo> result = new ArrayList<>();
		for(Order o:orderList){
			Query orderInfoQuery = dao.getQueryByHQL("FROM OrderInfo WHERE OrderID ="+o.getOrderID());
			List<OrderInfo> orderInfoList = orderInfoQuery.list();
			result.addAll(orderInfoList);
		}
		return result;
	}
}
