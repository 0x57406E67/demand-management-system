package com.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.OrderDao;
import com.demo.model.TOrder;
import com.demo.service.OrderService;
import com.demo.util.Constants;
import com.demo.util.HibernateProxyTypeAdapter;
import com.demo.util.PageBean;
import com.demo.util.Tools;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSerializationContext; 

import java.lang.reflect.Type;
@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderDaoImpl;
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void addOrder(TOrder order) {
		orderDaoImpl.addOrder(order);

	}
	/**
	 * 修改需求工单状态
	 * @param order
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void updateOrderStatus(TOrder order) {
		TOrder t_order=(TOrder)orderDaoImpl.getOrder(order.getId());
		t_order.setStatus(order.getStatus());
		orderDaoImpl.updateOrder(t_order);
	}
	@Override
	public void updateOrder(TOrder order) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public PageBean getOrderList(TOrder order,int pageSize, int page) {
        int count = orderDaoImpl.getCount(order); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(page);
        int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
        //int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
        int length = pageSize; // 每页记录数
        //int currentPage = PageBean.countCurrentPage(page);
		List<TOrder> orderList= orderDaoImpl.getOrderList(order,offset,length,null);
		for (TOrder tOrder : orderList) {
			if (Tools.notEmpty(tOrder.getStatus())) {
				if (tOrder.getStatus().equals(Constants.ADD_ORDER)) {
					tOrder.setStatus_name("新建需求");
				}
				else if (tOrder.getStatus().equals(Constants.ORDER_ANALYSE)) {
					tOrder.setStatus_name("需求分析");
				}
				else if (tOrder.getStatus().equals(Constants.ORDER_CONFIRM)) {
					tOrder.setStatus_name("需求确认");
				}
				else if (tOrder.getStatus().equals(Constants.ORDER_AUDIT)) {
					tOrder.setStatus_name("需求待审核");
				}
			}
			
		}
	       // 把分页信息保存到Bean中
	       PageBean pageBean = new PageBean();
	       pageBean.setPageSize(pageSize);
	       pageBean.setCurrentPage(currentPage);
	       pageBean.setAllRow(count);
	       pageBean.setTotalPage(totalPage);
	       pageBean.setList(orderList);
	       pageBean.init();
	   return pageBean;
	}
	
	@Override
	public List<TOrder> getmyOrderList(String userid) {

		List<TOrder> orderList= orderDaoImpl.getmyOrderList(userid);
		for (TOrder tOrder : orderList) {
			if (Tools.notEmpty(tOrder.getStatus())) {
				if (tOrder.getStatus().equals(Constants.ADD_ORDER)) {
					tOrder.setStatus_name("新建需求");
				}
				else if (tOrder.getStatus().equals(Constants.ORDER_ANALYSE)) {
					tOrder.setStatus_name("需求分析");
				}
				else if (tOrder.getStatus().equals(Constants.ORDER_CONFIRM)) {
					tOrder.setStatus_name("需求确认");
				}
				else if (tOrder.getStatus().equals(Constants.ORDER_AUDIT)) {
					tOrder.setStatus_name("需求待审核");
				}
			}
			
		}

	   return orderList;
	}
	/**
	 * 查询条件列表
	 */
	@Override
	public PageBean getOrderListJson(TOrder order,int pageSize, int page,String date_end) {
        int count = orderDaoImpl.queryGetCount(order, date_end); // 总记录数
        System.out.println("\nService中总条数：" + count);
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        System.out.println("\nService中总页数：" + totalPage);
        int currentPage = PageBean.countCurrentPage(page);
        System.out.println("\nService中当前页：" + currentPage);
        int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录   
        int length = pageSize; // 每页记录数  
		List<TOrder> list=orderDaoImpl.getOrderList(order,offset,length,date_end);
		for (TOrder tOrder : list) {
			if (Tools.notEmpty(tOrder.getStatus())) {
				if (tOrder.getStatus().equals(Constants.ADD_ORDER)) {
					tOrder.setStatus_name("新建需求");
				}
				else if (tOrder.getStatus().equals(Constants.ORDER_ANALYSE)) {
					tOrder.setStatus_name("需求分析");
				}
				else if (tOrder.getStatus().equals(Constants.ORDER_CONFIRM)) {
					tOrder.setStatus_name("需求确认");
				}
				else if (tOrder.getStatus().equals(Constants.ORDER_AUDIT)) {
					tOrder.setStatus_name("需求待审核");
				}
			}
		}
		//Gson gson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
		//注册GSON转换器：转换想要的JSON字段
		Gson gson=new GsonBuilder().registerTypeAdapter(TOrder.class, new JsonSerializer<TOrder>() {
            @Override
            public JsonElement serialize(TOrder src, Type typeOfSrc,
                    JsonSerializationContext context) {
                JsonObject o=new JsonObject();
                o.addProperty("id",  src.getId());
                o.addProperty("sysNo", src.getSysNo());
                o.addProperty("orderName",  src.getOrderName());
                o.addProperty("date", src.getDate());
                o.addProperty("orderApplyMan", src.getOrderApplyMan());
                o.addProperty("orderDept", src.getOrderDept());
                o.addProperty("gsmc", src.getCompany().getCompanyName());
                o.addProperty("developMan", src.getDevelopMan());
                //需求状态名称
                o.addProperty("status_name", src.getStatus_name());
                return o;
            }
        }).create();
		   String jsonList=gson.toJson(list);
	       // 把分页信息保存到Bean中
		   PageBean pageBean = new PageBean();
	       pageBean.setPageSize(pageSize);
	       pageBean.setCurrentPage(currentPage);
	       pageBean.setAllRow(count);
	       pageBean.setTotalPage(totalPage);
	       pageBean.setJsonList(jsonList);
	       pageBean.init();
		return pageBean;
	}
	@Override
	public TOrder getOrder(Integer id) {
		// TODO Auto-generated method stub
		return orderDaoImpl.getOrder(id);
	}
	@Override
	public List<Map<String, Object>> getStaticOrderCompany(Map<String, Object> parm) {
		
		return  orderDaoImpl.getStaticOrderCompany(parm);
	}

}
