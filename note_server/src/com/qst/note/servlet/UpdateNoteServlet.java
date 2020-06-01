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
//����ͻ����޸ı�����¼������
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
		
		NoteDao dao=new NoteDao();    //dao��
		Gson gson=new Gson();    //GSON�����࣬���ڽ����������JSON���ݷ��ظ��ͻ���
		Result result=new Result();    //��������
		
		//����һ�����ݣ�����������浽���result������
		result.isSuccess=dao.ModifyNote(id, title, content, noteTime);
		result.msg=result.isSuccess?"��¼�ɹ�":"����ʧ��";
		
		//�����������JSON����,ͨ����Ӧ���󣬷��ظ��ͻ���
		response.getWriter().append(gson.toJson(result));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
