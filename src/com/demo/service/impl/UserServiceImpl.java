package com.demo.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.UserDao;
import com.demo.dao.impl.UserDaoHimpl;
import com.demo.dao.impl.UserDaoImpl;
import com.demo.model.TResource;
import com.demo.model.TRole;
import com.demo.model.TUser;
import com.demo.service.UserService;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {
	@Resource(type = UserDaoHimpl.class)
	UserDao userDao;

	/**
	 * @param user
	 * @return true-success ，false-fail
	 */
	@Override
	public TUser checkLogin(TUser user) {
		TUser u = userDao.getUser(user);
		return u;
	}

	@Override
	// 用户注册
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public boolean regist(TUser userparm) {
		// TODO Auto-generated method stub
		try {
			userDao.save(userparm);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public TUser getUserById(int id) {
		// TODO Auto-generated method stub
		TUser user= userDao.getUserById(id);
//		Set<TRole> roles=user.getRoles();
//		System.out.println(roles.size());

		Hibernate.initialize(user.getRoles());

		return user;
	}
	/**
	 * 根据用户查询权限列表
	 * id:用户ID
	 */
	@Override
	public List<TResource> getResourceList(Integer id) {
		TUser user=userDao.getUserById(id);
		List<TRole> roles=user.getRoles();
		for (TRole tRole : roles) {
			List<TResource> resources=tRole.getResources();
			for (TResource tResource : resources) {

			}
		}
		return null;
	}
}
