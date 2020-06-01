package com.qst.note.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.qst.note.bean.UserBean;
import com.qst.note.util.DBUtil;

public class UserDao {
	
	//向User表中插入一条数据（注册）
	public boolean regist(UserBean user){
		Connection c=DBUtil.getConnection();
		try {
			PreparedStatement pst=(PreparedStatement) c.prepareStatement
					("insert into user(name,pass,tel,qq,wechat) values(?,?,?,?,?)");
			pst.setString(1, user.getName());
			pst.setString(2, user.getPass());
			pst.setString(3, user.getTel());
			pst.setString(4, user.getQq());
			pst.setString(5, user.getWechat());
			boolean result=pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//注册，处理业务登陆逻辑，登陆成功返回true，失败返回false
	public boolean login(String tel,String pass){
		Connection c=DBUtil.getConnection();
		PreparedStatement pst=null;
		try {
			pst=(PreparedStatement)c.prepareStatement("select pass from user where tel=?");
			pst.setString(1, tel);
			//结果集
			ResultSet rs=pst.executeQuery();    //查询时要用executeQuery()，因为要返回一个结果
			if(rs.first()){
				//获取数据库中的密码
				String passInDb=rs.getString("pass");
				//判断数据库中的密码和用户登陆的密码是否一致
				if(passInDb.equals(pass))
					return true;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close( c, pst, null);
		}
		return false;
	}
	
	//根据用户手机号返回用户ID
	public int getIDbyTel(String tel){
		Connection c=DBUtil.getConnection();
		PreparedStatement pst=null;
		int id=0;
		try {
			pst=(PreparedStatement) c.prepareStatement("select id from user where tel=?");
			pst.setString(1, tel);
			ResultSet rs=pst.executeQuery();
			if(rs.first()){
				id=rs.getInt("id");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close(c, pst, null);
		}
		return id;
	}
	
}
