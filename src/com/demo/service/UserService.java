package com.demo.service;
import java.util.List;
import com.demo.model.TResource;
import com.demo.model.TUser;

public interface UserService {

	public TUser checkLogin(TUser user);

	// 用户注册
	public boolean regist(TUser userparm);
	
	public TUser getUserById(int id);
	public List<TResource> getResourceList(Integer integer);

}
