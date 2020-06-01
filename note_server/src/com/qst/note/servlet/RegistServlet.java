package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.qst.note.bean.UserBean;
import com.qst.note.dao.UserDao;
import com.qst.note.result.Result;

//����AAP��ע������
@WebServlet("/regist")
public class RegistServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public RegistServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//��ȡ��Ϣ
		UserBean use=new UserBean();
		use.setName(request.getParameter("name"));
		use.setPass(request.getParameter("pass"));
		use.setTel(request.getParameter("tel"));
		
		//ע�ᣬ�ڱ��в������ݣ�������Ƿ����ɹ�
		UserDao userDao=new UserDao();
		Boolean b=userDao.regist(use);	
		
		//��װ��json�ַ���
		Result r=new Result();
		r.isSuccess=b;
		if(b){
			r.msg="��ϲ��ע��ɹ�";
		}
		else {
			r.msg="ע��ʧ�ܣ����ֻ����ѱ�ע��";
		}
		Gson gson=new Gson();
		String result=gson.toJson(r);
		
		//�������Ӧ���ͻ���
		response.getWriter().append(result);
	}

}
