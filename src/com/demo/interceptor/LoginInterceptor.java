package com.demo.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.demo.model.TUser;
import com.demo.util.Constants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 登录拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		TUser user=(TUser)session.getAttribute(Constants.CURRENT_LOGIN_USER);
		if(user==null){//返回登录界面
			return Action.LOGIN;
		}
		else{
			 return invocation.invoke();
		}
	}

}
