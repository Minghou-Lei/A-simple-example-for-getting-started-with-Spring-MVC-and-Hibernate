package BookStore.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class JDBCUtils {
	private static final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
	private static final String url = "jdbc:ucanaccess://./database/test.accdb";
	private static final String username = "";
	private static final String password = "";

	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	//Singleton
	private JDBCUtils(){}
	private static JDBCUtils instance;
	public static JDBCUtils getInstance(){
		if(instance==null)
			instance = new JDBCUtils();
		return instance;
	}

	//静态装载数据库连接驱动
	static {
		try {
			Class.forName(driver);
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	public static Connection getConnection(){
		try {
			Connection connection = DriverManager.getConnection(url,username,password);
			return connection;
		}catch (SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void release(){
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultSet = null;
		}
		if(preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			preparedStatement = null;
		}
		if(connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			connection = null;
		}
	}

	//对占位符“?”进行赋值
	private void setPreparedStatement(String[] parms) throws SQLException {
		if (parms != null && parms.length > 0) {
			for (int i = 0; i < parms.length; i++) {
				if("null".equals(parms[i])){
					preparedStatement.setNull(i + 1, Types.NULL);
				}else {
					preparedStatement.setString(i + 1, parms[i]);
				}
			}
		}
	}

	public List<Object[]> select(String sql, String[] parms) {
		try {
			//获取连接
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			//对sql中的占位符进行赋值
			setPreparedStatement(parms);
			//获得结果集合
			resultSet = preparedStatement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			//定义List存放每行数据
			List<Object[]> objectList = new ArrayList<>();
			//获取列数
			int columnCount = metaData.getColumnCount();
			//对结果集合进行遍历并将每行的数据存入Object[]中
			while (resultSet.next()) {
				//对象数组，表示一行数据
				Object[] objects = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					objects[i - 1] = resultSet.getObject(i);
				}
				//将数组存入list
				objectList.add(objects);
			}
			return objectList;
		} catch (Exception e) {
			System.err.println("查询失败！");
			e.printStackTrace();
		} finally {
			release();
		}
		return null;
	}

	public boolean addOrInsertOrUpdate(String sql, String[] parms){

		try {
			//获取连接
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			//对占位符进行赋值
			setPreparedStatement(parms);
			//提交sql
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e){
			System.err.println("表更新失败!");
			e.printStackTrace();
		} finally {
			release();
		}
		return false;
	}

}
