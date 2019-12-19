package BookStore.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Order")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OrderID;

	private int UserID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date OrderDate;

	private float Amount;

	@Column(nullable = true)
	private String State;

	public Order() {
	}

	public Order(int ID) {
		this.OrderID = ID;
	}

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public float getAmount() {
		return Amount;
	}

	public void setAmount(float amount) {
		Amount = amount;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	@Override
	public String toString() {
		return "OrderID: " + OrderID + "\nOrderDate: " + OrderDate + "\nAmount: ￥" + Amount + "\nState: " + State;
	}
}
