package com.demo.service;

import com.demo.model.TAttach;

public interface AttachService {
	
	public void addAttach(TAttach attach);

	public void backFillOrderId(Integer int_attach_id, Integer id);
}
