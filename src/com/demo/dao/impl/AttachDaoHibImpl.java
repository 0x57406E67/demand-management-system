package com.demo.dao.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.demo.dao.AttachDao;
import com.demo.model.TAttach;
import com.demo.model.TOrder;

@Repository
public class AttachDaoHibImpl implements AttachDao {
	Logger logger = Logger.getLogger(AttachDaoHibImpl.class);
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void addAttach(TAttach attach) {
		Session session = sessionFactory.getCurrentSession();
		session.save(attach);

	}

	@Override
	public void updateOrderId(Integer int_attach_id, Integer id) {
		Session session = sessionFactory.getCurrentSession();
		TAttach attach = (TAttach) session.get(TAttach.class, int_attach_id);
		TOrder order = new TOrder();
		order.setId(id);
		attach.setTOrder(order);
		session.update(attach);
	}

}
