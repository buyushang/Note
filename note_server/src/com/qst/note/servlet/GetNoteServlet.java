package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.note.bean.NoteBean;
import com.qst.note.dao.NoteDao;

@WebServlet("/GetNoteServlet")
//处理客户端根据id获取一条note的请求
public class GetNoteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
 
    public GetNoteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	
		//将获取的参数id转换成int类型
		int id=Integer.valueOf(request.getParameter("id"));
		NoteDao dao=new NoteDao();
		NoteBean note=dao.getNoteById(id);
		Gson gson=new Gson();
		
		response.getWriter().append(gson.toJson(note));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
