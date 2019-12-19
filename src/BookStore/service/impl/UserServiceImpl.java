package BookStore.service.impl;

import BookStore.dao.impl.BookStoreDaoImpl;
import BookStore.domain.UserInfo;
import BookStore.service.UserService;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

	//持久化操作数据库
	private BookStoreDaoImpl dao;
	private UserInfo user;

	public UserInfo getUser() {
		return user;
	}

	public UserServiceImpl() {
		dao = new BookStoreDaoImpl();
		dao.makeConnection();
	}

	public UserServiceImpl(String name, String password) {
		this.dao = new BookStoreDaoImpl();
		dao.makeConnection();
		Query userQuery = dao.getQueryByHQL("FROM UserInfo WHERE UserName = \'" + name + "\' AND Password = \"" + password + "\'");
		List<UserInfo> userInfos = userQuery.list();
		if (userInfos.size() <= 0) {
			System.out.println("Username or password error!");
			throw new RuntimeException("Username or password error!");
		}
		this.user = new UserInfo(userInfos.get(0));
	}

	public UserServiceImpl(BookStoreServiceImpl bssi){
		dao = new BookStoreDaoImpl();
		dao.makeConnection();
		int userID = bssi.getUserID();
		Query userQuery = dao.getQueryByHQL("FROM UserInfo WHERE UserID = "+userID);
		List<UserInfo> userInfoList = userQuery.list();
		this.user = userInfoList.get(0);
	}

	@Override
	public void register(String username, String password, String sex, Date birthday, String state) {
		UserInfo newUser = new UserInfo(username, sex, password, birthday, state);
		this.user = new UserInfo(newUser);
		Query userQuery = dao.getQueryByHQL("FROM UserInfo WHERE UserName = \'" + username + "\' AND Password = \'" + password + "\'");
		List<UserInfo> userInfos = userQuery.list();
		if (userInfos.size() > 0) {
			System.out.println("Such user has existed!");
			throw new RuntimeException("Such user has existed!");
		} else {
			this.user.setUserID(dao.insert(newUser));
		}
	}

	@Override
	public void changePassword(String newPassword) {
		this.user.setPassword(newPassword);
		dao.update(user);
	}

	@Override
	public void deleteUser() {
		dao.delete(user);
	}

}
