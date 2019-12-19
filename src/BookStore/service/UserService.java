package BookStore.service;

import java.util.Date;

//用户服务 业务逻辑层接口
public interface UserService {

	//注册新用户
	void register(String username, String password, String sex, Date birthday, String state);

	//更改密码
	void changePassword(String newPassword);

	//注销用户
	void deleteUser();

}







