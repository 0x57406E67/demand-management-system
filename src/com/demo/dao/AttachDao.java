package com.demo.dao;

import com.demo.model.TAttach;

public interface AttachDao {

	public void addAttach(TAttach attach);

	public void updateOrderId(Integer int_attach_id, Integer id);
}
