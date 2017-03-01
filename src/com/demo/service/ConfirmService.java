package com.demo.service;

import com.demo.model.TAttach;
import com.demo.model.TConfirm;

public interface ConfirmService {
	/**
	 * 添加需求确认
	 * @param comfirm
	 */
	public void addConfirm(TConfirm comfirm,TAttach attach1,TAttach attach2);
}
