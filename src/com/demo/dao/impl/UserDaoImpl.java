package com.demo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.demo.dao.UserDao;
import com.demo.model.TResource;
import com.demo.model.TUser;
import com.demo.util.JdbcTemplateCommon;

public class UserDaoImpl extends JdbcTemplateCommon implements UserDao {

	@Override
	public TUser getUser(TUser user) {
		// TODO Auto-generated method stub
		String sql = "from t_user u where u.login_name='"
				+ user.getLogin_name() + "' and u.login_password='"
				+ user.getLogin_password() + "'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if (list != null && list.size() > 0) {
			Map<String, Object> map = list.get(0);
			TUser ret = new TUser();
			ret.setLogin_name(map.get("LOGIN_NAME").toString());
			ret.setLogin_password(map.get("LOGIN_PASSWORD").toString());
			return ret;
		}
		return null;
	}

	@Override
	public void save(TUser userparm) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public TUser getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  List<TResource> getResourceList(Integer id) {
		return null;
	}

}
