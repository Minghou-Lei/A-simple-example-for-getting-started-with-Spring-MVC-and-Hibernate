package BookStore.domain;

import java.io.Serializable;

public class OrderInfoKey implements Serializable {
	private static final long serialVersionUID = 3176972128965536016L;
	private int OrderID;
	private int BookID;

	public OrderInfoKey() {
	}

	public OrderInfoKey(int OrderID, int BookID) {
		this.OrderID = OrderID;
		this.BookID = BookID;
	}

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public int getBookID() {
		return BookID;
	}

	public void setBookID(int bookID) {
		BookID = bookID;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OrderInfoKey) {
			OrderInfoKey key = (OrderInfoKey) obj;
			if (this.BookID == key.BookID && this.OrderID == key.OrderID) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}






