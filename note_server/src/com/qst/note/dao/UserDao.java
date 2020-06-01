package com.qst.note.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.qst.note.bean.UserBean;
import com.qst.note.util.DBUtil;

public class UserDao {
	
	//��User���в���һ�����ݣ�ע�ᣩ
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
	
	//ע�ᣬ����ҵ���½�߼�����½�ɹ�����true��ʧ�ܷ���false
	public boolean login(String tel,String pass){
		Connection c=DBUtil.getConnection();
		PreparedStatement pst=null;
		try {
			pst=(PreparedStatement)c.prepareStatement("select pass from user where tel=?");
			pst.setString(1, tel);
			//�����
			ResultSet rs=pst.executeQuery();    //��ѯʱҪ��executeQuery()����ΪҪ����һ�����
			if(rs.first()){
				//��ȡ���ݿ��е�����
				String passInDb=rs.getString("pass");
				//�ж����ݿ��е�������û���½�������Ƿ�һ��
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
	
	//�����û��ֻ��ŷ����û�ID
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
