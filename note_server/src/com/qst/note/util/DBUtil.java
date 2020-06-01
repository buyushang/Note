package com.qst.note.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



//数据库工具类DBUtil
public class DBUtil {
	
	static String dbName="note_db";    //数据库名
	static String name="root";    //数据库用户名
	static String pass="911419";    //数据库密码
	
	/*
	//main方法测试一下是否能连接到数据库
	public static void main(String[] args) {
		Connection c=getConnection();
		try {
			Statement st=(Statement) c.createStatement();
			st.execute("insert into user(name,pass) values('zhangsan','123')");
			close(c,st, null);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
	
	//连接数据库对象
	public static Connection getConnection(){
		Connection c=null;
		try {
			//加载MYSQL的JDBC驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取数据库连接
			c=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?ssl=true",name,pass);
			} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	//释放资源
	public static void close(Connection c,Statement stat,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stat!=null){
			try {
				stat.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(c!=null){
			try {
				c.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
