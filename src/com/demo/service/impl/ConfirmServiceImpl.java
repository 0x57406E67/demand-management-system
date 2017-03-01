package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.AttachDao;
import com.demo.dao.ConfirmDao;
import com.demo.model.TAttach;
import com.demo.model.TConfirm;
import com.demo.service.ConfirmService;
@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class ConfirmServiceImpl implements ConfirmService {
	@Autowired
	ConfirmDao confirmDao;
	@Autowired
	AttachDao attachDao;
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void addConfirm(TConfirm comfirm,TAttach attach1,TAttach attach2) {
		//附件表添加1（需求确认表）
		attachDao.addAttach(attach1);
		//附件表添加2（工作量预估表）
		attachDao.addAttach(attach2);
		//添加需求分析
		confirmDao.addConfirm(comfirm);

	}

}
