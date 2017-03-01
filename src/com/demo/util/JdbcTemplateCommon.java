package com.demo.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateCommon {
	Logger logger=Logger.getLogger(this.getClass());
	@Autowired
	protected JdbcTemplate jdbcTemplate;
}
