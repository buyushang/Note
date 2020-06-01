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

//处理AAP端注册请求
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
		//获取信息
		UserBean use=new UserBean();
		use.setName(request.getParameter("name"));
		use.setPass(request.getParameter("pass"));
		use.setTel(request.getParameter("tel"));
		
		//注册，在表中插入数据，并检测是否插入成功
		UserDao userDao=new UserDao();
		Boolean b=userDao.regist(use);	
		
		//封装成json字符串
		Result r=new Result();
		r.isSuccess=b;
		if(b){
			r.msg="恭喜您注册成功";
		}
		else {
			r.msg="注册失败，该手机号已被注册";
		}
		Gson gson=new Gson();
		String result=gson.toJson(r);
		
		//将结果响应给客户端
		response.getWriter().append(result);
	}

}
