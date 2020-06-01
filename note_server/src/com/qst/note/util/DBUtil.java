package com.qst.note.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



//���ݿ⹤����DBUtil
public class DBUtil {
	
	static String dbName="note_db";    //���ݿ���
	static String name="root";    //���ݿ��û���
	static String pass="911419";    //���ݿ�����
	
	/*
	//main��������һ���Ƿ������ӵ����ݿ�
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
	
	//�������ݿ����
	public static Connection getConnection(){
		Connection c=null;
		try {
			//����MYSQL��JDBC����
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ���ݿ�����
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
	
	//�ͷ���Դ
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
