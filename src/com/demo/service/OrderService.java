package com.demo.service;

import java.util.List;
import java.util.Map;

import com.demo.model.TOrder;
import com.demo.util.PageBean;

public interface OrderService {
	/**
	 * 
	 * @param order
	 * @return
	 */
	public PageBean getOrderList(TOrder order,int pageSize, int currentPage);
	/**
	 * 
	 * @param order
	 * @return
	 */
	public List<TOrder> getmyOrderList(String userid);
	/**
	 * 
	 * @param order
	 * @return
	 */
	public PageBean getOrderListJson(TOrder order,int pageSize, int currentPage,String date_end);
	/**
	 * 添加需求工单
	 * @param order
	 */
	public void addOrder(TOrder order);
	
	/**
	 * 修改需求工单
	 * @param order
	 */
	public void updateOrder(TOrder order);
	/**
	 * 修改需求工单状态
	 * @param order
	 */
	public void updateOrderStatus(TOrder order);
	
	/**
	 * 获取需求工单
	 * @param order
	 */
	public TOrder getOrder(Integer id); 
	/**
	 * 需求与厂商的统计视图
	 * @param parm
	 * @return
	 */
	public List<Map<String,Object>> getStaticOrderCompany(Map<String,Object> parm);

}
