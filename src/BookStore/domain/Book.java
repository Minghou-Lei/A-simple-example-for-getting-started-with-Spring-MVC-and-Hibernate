package BookStore.domain;

import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "Book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int BookID;

	private int ClassID;
	private String BookName;
	private String Author;
	private String Publish;
	private float Price;
	private int Inventory;

	public Book() {
	}

	public Book(Book b) {
		this.Author = b.Author;
		this.BookID = b.BookID;
		this.BookName = b.BookName;
		this.ClassID = b.ClassID;
		this.Inventory = b.Inventory;
		this.Price = b.Price;
		this.Publish = b.Publish;
	}

	public Book(int ID) {
		this.BookID = ID;
	}

	@Override
	public String toString() {
		return ("\tBookName: " + BookName + "\n\tBookID: " + BookID + "\n\tAuthor: " + Author + "\n\tPublish: " + Publish + "\n\tPrice: ￥" + Price + "\n\tInventory: " + Inventory);
	}

	public int getBookID() {
		return BookID;
	}

	public void setBookID(int bookID) {
		BookID = bookID;
	}

	public int getClassID() {
		return ClassID;
	}

	public void setClassID(int classID) {
		ClassID = classID;
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getPublish() {
		return Publish;
	}

	public void setPublish(String publish) {
		Publish = publish;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public int getInventory() {
		return Inventory;
	}

	public void setInventory(int inventory) {
		Inventory = inventory;
	}
}








