package com.demo.dao.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.demo.dao.AnaylzeDao;
import com.demo.model.TAnaylze;
import com.demo.model.TOrder;
@Repository
public class AnaylzeDaoHibImpl implements AnaylzeDao {
	Logger logger=Logger.getLogger(OrderDaoHibImpl.class);
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	@Override
	public void addAnaylze(TAnaylze anaylze) {
		Session  session = sessionFactory.getCurrentSession();
		session.save(anaylze);
	}

}
