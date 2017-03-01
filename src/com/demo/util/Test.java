package com.demo.util;

import java.util.ArrayList;
import java.util.List;

import com.demo.model.TUser;

public class Test {
	public static void main(String[] args) {
		TUser u1=new TUser();
		u1.setId(1);
		u1.setLogin_name("zhangsan");
		TUser u2=new TUser();
		u2.setId(2);
		u1.setLogin_name("zhangsan2");
		List<TUser> list=new ArrayList<TUser>();
		String json=JsonUtil.beanToJson(u1);
		System.out.println(json);
	}

}
