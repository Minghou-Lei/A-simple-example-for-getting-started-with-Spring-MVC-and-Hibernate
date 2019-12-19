package BookStore.domain;

import javax.persistence.Table;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UserInfo")
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int UserID;

	@Column(nullable = false)
	private String UserName;

	private String Sex;

	@Column(nullable = false)
	private String Password;

	@Temporal(TemporalType.DATE)
	private Date Birthday;

	private String State;

	public UserInfo() {
	}

	public UserInfo(UserInfo obj){
		this.Birthday = obj.getBirthday();
		this.Password = obj.getPassword();
		this.Sex = obj.getSex();
		this.State = obj.getState();
		this.UserID = obj.getUserID();
		this.UserName = obj.getUserName();
	}

	public UserInfo(String userName, String sex, String password, Date birthday, String state) {
		UserName = userName;
		Sex = sex;
		Password = password;
		Birthday = birthday;
		State = state;
	}

	public UserInfo(int ID) {
		this.UserID = ID;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Date getBirthday() {
		return Birthday;
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}
}
