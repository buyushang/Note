package com.qst.note.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.qst.note.bean.NoteBean;
import com.qst.note.util.DBUtil;

public class NoteDao {

	//向表note_table插入一条数据
	public Boolean insert(String title,String content,String noteTime,String tel){
		
		int id=new UserDao().getIDbyTel(tel);    //根据用户电话获取用户ID
		//如果用户ID小于1表示用户不存在，返回false
		if(id<1){
			return false;
		}
		Connection c=DBUtil.getConnection();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String nowTime=sdf.format(new Date());    //获取当前时间
		
		try {
			PreparedStatement pst=(PreparedStatement) c.prepareStatement
					("insert into note_table(title,content,note_time,user_id,create_time) values(?,?,?,?,?)");
			pst.setString(1,title);
			pst.setString(2,content);
			pst.setString(3,noteTime);
			pst.setInt(4,id);
			pst.setString(5,nowTime);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	//根据id查询数据库并返回一条note
	public NoteBean getNoteById(int id){
		NoteBean note=new NoteBean();
		Connection c=DBUtil.getConnection();
		try {
			PreparedStatement pst=(PreparedStatement) 
					c.clientPrepareStatement("select * from note_table where id=?");
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			if(rs.first()){
				note.setId(id);
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setCreatTime(rs.getString("create_time"));
				note.setUpdateTime(rs.getString("update_time"));
				note.setNoteTime(rs.getString("note_time"));
				note.setUserId(rs.getInt("user_id"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return note;
	}
	
	//根据id修改服务器数据库中某一条备忘记录，修改成功返回true，否则返回false
	public boolean ModifyNote(int id,String title,String content,String noteTime){
		//如果用户id小于1表示用户不存在，返回false
		if(id<1){
			return false;
		}
		Connection c=DBUtil.getConnection();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String nowTime=sdf.format(new Date());
		
		try {
			PreparedStatement pst=(PreparedStatement) 
					c.prepareStatement("update note_table set title=?,content=?,note_time=?,update_time=? where id=?");
			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, noteTime);
			pst.setString(4, nowTime);
			pst.setInt(5, id);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
