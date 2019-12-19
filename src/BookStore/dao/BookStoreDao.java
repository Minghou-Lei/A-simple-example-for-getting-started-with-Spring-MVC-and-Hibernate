package BookStore.dao;

import org.hibernate.query.Query;

//数据库访问 ( 持久化 ) 接口
public interface BookStoreDao {

	//从domain包中获取实体类生成映射文件到数据库表
	void makeConnection();

	//作为用户登录购书；成功登录返回UserID，用户名或密码错误返回值 -1
	int login(String name, String password);

	//增： 在数据库中插入记录并返回主键ID
	int insert(Object o);

	//增： 仅在数据库中插入记录（ 用于没有主键的表的插入 ）
	void insertWithoutReturn(Object o);

	//删： 从数据库中删除某条记录
	void delete(Object o);

	//改： 从数据库中更新某条记录
	void update(Object o);

	//查： 从HQL语句获得Query（ 可以获得类似于JDBC的ResultSet ）
	Query getQueryByHQL(String HQL);

	//查： 从SQL语句获得Query
	Query getQueryBySQL(String SQL);

}






