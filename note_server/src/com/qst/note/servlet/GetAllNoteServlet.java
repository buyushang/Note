package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.qst.note.bean.NoteBean;
import com.qst.note.dao.NoteDao;

@WebServlet("/GetAllNoteServlet")
//�����û���ȡ���б���¼������
public class GetAllNoteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public GetAllNoteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");  //���ñ����ʽ
		
		String tel = request.getParameter("tel");   //��ȡ�������tel
		ArrayList<NoteBean> all = new NoteDao().getAllNotes(tel);    //����
		
		response.getWriter().append(new Gson().toJson(all));    //������ת��ΪJson�ַ���
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}