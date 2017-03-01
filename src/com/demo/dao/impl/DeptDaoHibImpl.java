package com.demo.dao.impl;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.demo.dao.DeptDao;
import com.demo.model.TDepart;
import com.demo.model.TUser;

@Repository
public class DeptDaoHibImpl implements DeptDao {
	Logger logger = Logger.getLogger(UserDaoHimpl.class);
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public TDepart getDeptById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from TDept d where d.id=? ";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		TDepart dept = (TDepart) query.uniqueResult();
		// 打印测试： 部门下属的所有人员
		// System.out.println(dept.getDeptName()+"下属用户：-------------------》");
		// Set<TUser> users=dept.getTUsers();
		// Iterator<TUser> its=users.iterator();
		// while(its.hasNext()){
		// TUser u=its.next();
		// System.out.println(u.getLogin_name());
		// }
		return dept;
	}

}
