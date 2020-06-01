package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.note.dao.NoteDao;
import com.qst.note.result.Result;

@WebServlet("/UpdateNoteServlet")
//处理客户端修改备忘记录的请求
public class UpdateNoteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public UpdateNoteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		int id=Integer.parseInt(request.getParameter("id"));
		String noteTime=request.getParameter("noteTime");
		
		NoteDao dao=new NoteDao();    //dao类
		Gson gson=new Gson();    //GSON解析类，用于将结果解析成JSON数据返回给客户端
		Result result=new Result();    //请求结果类
		
		//插入一条数据，并将结果保存到结果result对象中
		result.isSuccess=dao.ModifyNote(id, title, content, noteTime);
		result.msg=result.isSuccess?"记录成功":"保存失败";
		
		//将结果解析成JSON数据,通过响应对象，返回给客户端
		response.getWriter().append(gson.toJson(result));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
