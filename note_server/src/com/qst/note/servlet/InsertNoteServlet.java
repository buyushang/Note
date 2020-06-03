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

@WebServlet("/InsertNoteServlet")

public class InsertNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertNoteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");    //���ñ����ʽ
		
		//��ȡ�������
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String tel=request.getParameter("tel");
		String noteTime=request.getParameter("noteTime");    
		
		NoteDao dao=new NoteDao();    //dao��
		Gson gson = new Gson();    //Gson�����࣬���ڽ����������JSON���ݷ��ؿͻ���
		Result result=new Result();    //��������
		
		//����һ�����ݣ�����������浽���result������
		result.isSuccess=dao.insert(title, content, noteTime, tel);   
		result.msg=result.isSuccess?"��¼�ɹ�":"����ʧ��";
		
		//�����������JSON����ͨ����Ӧ���󣬷��ظ��ͻ���
		response.getWriter().append(gson.toJson(result));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
