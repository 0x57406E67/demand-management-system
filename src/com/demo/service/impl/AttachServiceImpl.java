package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.AttachDao;
import com.demo.model.TAttach;
import com.demo.service.AttachService;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AttachServiceImpl implements AttachService {
	@Autowired
	AttachDao attachDao;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void addAttach(TAttach attach) {
		attachDao.addAttach(attach);

	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void backFillOrderId(Integer int_attach_id, Integer id) {
		attachDao.updateOrderId(int_attach_id, id);

	}

}
