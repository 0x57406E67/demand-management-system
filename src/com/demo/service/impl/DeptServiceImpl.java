package com.demo.service.impl;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.DeptDao;
import com.demo.model.TDepart;
import com.demo.model.TUser;
import com.demo.service.DeptService;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class DeptServiceImpl implements DeptService {
	@Autowired
	DeptDao deptDao;

	@Override
	public TDepart getDeptById(int id) {
		// TODO Auto-generated method stub
		TDepart dept = deptDao.getDeptById(id);
		Set<TUser> users = dept.getTUsers();
		Iterator<TUser> its = users.iterator();
		while (its.hasNext()) {
			TUser u = its.next();
			System.out.println(u.getLogin_name());
		}
		return dept;
	}

}
