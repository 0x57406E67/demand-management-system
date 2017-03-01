package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.AnaylzeDao;
import com.demo.model.TAnaylze;
import com.demo.service.AnaylzeService;
@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class AnaylzeServiceImpl implements AnaylzeService {
	@Autowired
	private AnaylzeDao anaylzeDao;
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void addAnaylze(TAnaylze anaylze) {
		anaylzeDao.addAnaylze(anaylze);

	}

}
