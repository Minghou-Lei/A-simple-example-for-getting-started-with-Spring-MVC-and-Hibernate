package BookStore.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "OrderInfo")
@IdClass(OrderInfoKey.class)
//Hibernate操作无主键的OrderInfo表需要联合主键
public class OrderInfo implements Serializable {

	//联合主键开始

	@Id
	@Column(name = "BookID")
	private int BookID;

	@Id
	@Column(name = "OrderID")
	private int OrderID;

	//联合主键结束

	@Column(name = "Quantity", nullable = false)
	private int Quantity;

	public int getBookID() {
		return BookID;
	}

	public void setBookID(int bookID) {
		BookID = bookID;
	}

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
}



