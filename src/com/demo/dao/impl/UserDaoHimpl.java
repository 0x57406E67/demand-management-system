package com.demo.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.demo.dao.UserDao;
import com.demo.model.TResource;
import com.demo.model.TUser;

@Repository
public class UserDaoHimpl implements UserDao {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public TUser getUser(TUser user) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from TUser u where u.login_name=? and u.login_password=? ";
		Query query = session.createQuery(hql);
		query.setString(0, user.getLogin_name());
		query.setString(1, user.getLogin_password());
		TUser u = (TUser) query.uniqueResult();
		if (u != null) {
			System.out.println(u.getLogin_name());
		}
		return u;
	}

	@Override
	public void save(TUser userparm) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(userparm);
	}
	
	@Override
	public TUser getUserById(int id) {
		Session  session = sessionFactory.getCurrentSession();
		TUser user=(TUser)session.get(TUser.class, id);
		return user;
	}
	
	@Override
	public List<TResource> getResourceList(Integer id) {
		Session  session = sessionFactory.getCurrentSession();
		return null;
		
		
	}
	
	

}
