package com.demo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.demo.model.TDept;
import com.demo.model.TResource;
import com.demo.model.TRole;
import com.demo.model.TUser;
import com.demo.service.DeptService;
import com.demo.service.UserService;
import com.demo.service.impl.UserServiceImpl;
import com.demo.util.Constants;
import com.demo.util.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 用户控制器
 * 
 * @author chenfeng
 * 
 */
@Results({ @Result(name = "to_login", location = "/login.jsp"),
		@Result(name = "main", location = "/WEB-INF/jsp/main.jsp"),

})
@Namespace("/user")
@Controller
public class UserAction extends ActionSupport {
	private TUser userparm;

	Logger logger = Logger.getLogger(UserAction.class);
	@Autowired
	UserService userServiceImpl;
	@Autowired
	DeptService deptService;

	public UserService getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	/**
	 * 用户登录 参数JSON格式：{"login_name":"zhangsan","login_password":"1111"}
	 */
	@Action(value = "loginUser")
	public void login() {
		logger.info("login method is called");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		PrintWriter out = null;
		String jsonStr = request.getParameter("parm");
		logger.info(jsonStr);
		// 解析JSON串
		Gson gson = new GsonBuilder().create();
		userparm = gson.fromJson(jsonStr, TUser.class);

		try {
			out = response.getWriter();
			logger.info(userparm);
			TUser user = userServiceImpl.checkLogin(userparm);
			List<TResource> resources=userServiceImpl.getResourceList(user.getId());
			if (user != null) {
				// 登录成功-用户信息保存至session
				session.setAttribute(Constants.CURRENT_LOGIN_USER, user);
				//登录成功-权限列表保存至session
				session.setAttribute(Constants.PERMITS, resources);
				out.print("{\"result\":\"success\"}");
			} else {
				out.print("{\"result\":\"fail\"}");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	/**
	 * 用户注册 参数JSON格式：{"login_name":"zhangsan","login_password":"1111"}
	 */
	@Action(value = "registUser")
	public void regist() {
		logger.info("regist method is called");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		String jsonStr = request.getParameter("parm");
		logger.info(jsonStr);
		// 解析JSON串
		Gson gson = new GsonBuilder().create();
		userparm = gson.fromJson(jsonStr, TUser.class);

		try {
			out = response.getWriter();
			logger.info(userparm);
			boolean result = userServiceImpl.regist(userparm);
			if (result) {
				out.print("{\"result\":\"success\"}");
			} else {
				out.print("{\"result\":\"fail\"}");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}

	}
  /**
   * 异步验证
   * @return
   * @throws Exception
   */
	/*public String exists() throws Exception{
		boolean boo=dao.exists(admin.getUsername());
		  //获取原始的PrintWriter对象,以便输出响应结果,而不用跳转到某个试图    
		        HttpServletResponse response = ServletActionContext.getResponse();    
		        //设置字符集    
		        response.setCharacterEncoding("UTF-8");    
		        PrintWriter out = response.getWriter();    
		if(boo){	     
		        //直接输入响应的内容    
		        out.println("*用户名已存在*");    
		       / /格式化输出时间  
		        out.flush();    
		        out.close();    
		}
		out.println("*用户名可用*"); 
		return null;
		}*/
	
	@Action(value = "to_login_page")
	public String to_login() {

		return "to_login";
	}

	/*
	 * 跳转首页 return
	 */
	@Action(value = "main")
	public String to_main() {

		return "main";
	}

	public TUser getUserparm() {
		return userparm;
	}

	public void setUserparm(TUser userparm) {
		this.userparm = userparm;
	}

}
