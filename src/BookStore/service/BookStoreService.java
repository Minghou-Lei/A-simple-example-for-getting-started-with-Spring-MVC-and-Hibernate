package BookStore.service;

//书店服务 业务逻辑层接口
public interface BookStoreService {

	//打印书单
	String printBooks();

	//输入 BookID 和购买数量买书
	void buyBook(int bookID, int num);

	//打印订单
	void printOrderInfo();

	//取消正在”Shipping“状态下的订单
	void cancelOrder(int OrderID);

}







