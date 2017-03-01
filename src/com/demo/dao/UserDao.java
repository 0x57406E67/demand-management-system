package com.demo.dao;

import java.util.List;

import com.demo.model.TResource;
import com.demo.model.TUser;

public interface UserDao {

	public TUser getUser(TUser user);

	// 用户注册
	public void save(TUser userparm);
	
	public TUser getUserById(int id);
	
	public List<TResource> getResourceList(Integer id);
}
