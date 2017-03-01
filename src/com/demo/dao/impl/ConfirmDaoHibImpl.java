package com.demo.dao.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.demo.dao.ConfirmDao;
import com.demo.model.TAnaylze;
import com.demo.model.TConfirm;
@Repository
public class ConfirmDaoHibImpl implements ConfirmDao {
	Logger logger=Logger.getLogger(OrderDaoHibImpl.class);
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	@Override
	public void addConfirm(TConfirm comfirm) {
		Session  session = sessionFactory.getCurrentSession();
		session.save(comfirm);

	}

}
