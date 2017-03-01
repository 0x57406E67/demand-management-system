package com.demo.dao;

import java.util.List;
import java.util.Map;

import com.demo.model.TOrder;

public interface OrderDao {
	/**
	 * 添加需求工单
	 * @param order
	 */
	public void addOrder(TOrder order);
	
	/**
	 * 获取需求工单
	 * @param order
	 */
	public TOrder getOrder(Integer id);
	/**
	 * 修改需求工单
	 * @param order
	 */
	public void updateOrder(TOrder order) ;
	/**
	 *  列表展示需求工单
	 * @param order
	 */
	public List<TOrder> getOrderList(TOrder order,int offset,int length,String date_end);
	/**
	 *  总记录条数
	 */
     public int getCount(TOrder order);
     /**
      * 查询条件的总条数
      */
     public int queryGetCount(TOrder order,String date_end);
	/**
	 * 需求与厂商的统计
	 * @param parm
	 * @return
	 */

	public List<Map<String, Object>> getStaticOrderCompany(Map<String,Object> parm);
	
	public List<TOrder> getmyOrderList(String userid);


}
