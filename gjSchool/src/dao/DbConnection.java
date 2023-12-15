//統一把連線的動作寫成一個 method

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {//要視為和下面的動態 class 是兩個 class

	public static void main(String[] args) {
		//DbConnection db=new DbConnection();
		//System.out.println(db.name);
		System.out.println(DbConnection.getDb());

	}
	
	String name;
	public static Connection getDb()
	{
		Connection conn=null;
		String url="jdbc:mysql://localhost:3306/school";
		String user="root";
		String password="12345678";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			System.out.println("No Driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("No Connection");
			e.printStackTrace();
		}
		return conn;
	}

}
