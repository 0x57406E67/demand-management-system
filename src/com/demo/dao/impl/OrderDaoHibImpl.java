package com.demo.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.demo.dao.OrderDao;
import com.demo.model.TOrder;
import com.demo.util.Tools;
@Repository
public class OrderDaoHibImpl implements OrderDao {
	Logger logger=Logger.getLogger(OrderDaoHibImpl.class);
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	@Override
	public void addOrder(TOrder order) {
		Session  session = sessionFactory.getCurrentSession();
		session.save(order);
	}
	@Override
	public TOrder getOrder(Integer id) {
		Session  session = sessionFactory.getCurrentSession();
		TOrder order=(TOrder)session.get(TOrder.class, id);
		return order;
	}
	@Override
	public void updateOrder(TOrder order) {
		Session  session = sessionFactory.getCurrentSession();
		session.update(order);
	}
	/**
	 * 列表展示工单需求列表
	 * @param order  查询条件
	 */
	@Override
	public List<TOrder> getOrderList(TOrder order,int offset, int length,String date_end) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from TOrder o where 1=1 ";
		if (order != null) {
			hql += "and o.sysNo like:sysNo and o.orderName like:orderName " 
					+ "and o.orderApplyMan like:orderApplyMan and o.orderDept like:orderDept " 
					+ "and o.developMan like:developMan ";
			if(date_end.length() != 0){
				hql += "and o.date BETWEEN '"+order.getDate()+"' and '"+date_end+"'";
			}
			if(order.getCompany_id() != 0){
				hql += "and o.company.id='"+order.getCompany_id()+"'";
			}
		}
		Query query = session.createQuery(hql);
		if (order != null) {
			query.setParameter("sysNo", "%" + order.getSysNo() + "%");
			query.setParameter("orderName", "%" + order.getOrderName() + "%");
			query.setParameter("orderApplyMan", "%" + order.getOrderApplyMan() + "%");
			query.setParameter("orderDept", "%" + order.getOrderDept() + "%");
			query.setParameter("developMan", "%" + order.getDevelopMan() + "%");
		}
		query.setFirstResult(offset);
		query.setMaxResults(length);

		List<TOrder> orderList = query.list();
		logger.info(orderList);
		return orderList;
	}
  
	     @Override
	     public int getCount(TOrder order) {
	    	 Session session = sessionFactory.getCurrentSession();
	 		 String hql = "select count(*) from TOrder";
	         Query q = session.createQuery(hql);
	         return Integer.parseInt(q.list().get(0).toString());
	     }
	     
	     public int queryGetCount(TOrder order,String date_end){
	 		Session session = sessionFactory.getCurrentSession();
			String hql = "from TOrder o where 1=1 ";
			if (order != null) {
				hql += "and o.sysNo like:sysNo and o.orderName like:orderName " 
						+ "and o.orderApplyMan like:orderApplyMan and o.orderDept like:orderDept " 
						+ "and o.developMan like:developMan ";
				if(date_end.length() != 0){
					hql += "and o.date BETWEEN '"+order.getDate()+"' and '"+date_end+"'";
				}
				if(order.getCompany_id() != 0){
					hql += "and o.company.id='"+order.getCompany_id()+"'";
				}
			}
			Query query = session.createQuery(hql);
			if (order != null) {
				query.setParameter("sysNo", "%" + order.getSysNo() + "%");
				query.setParameter("orderName", "%" + order.getOrderName() + "%");
				query.setParameter("orderApplyMan", "%" + order.getOrderApplyMan() + "%");
				query.setParameter("orderDept", "%" + order.getOrderDept() + "%");
				query.setParameter("developMan", "%" + order.getDevelopMan() + "%");
			}
			List<TOrder> orderList = query.list();
			System.out.println("\norderList鍏辨湁锛" + orderList.size());
			return orderList.size();
	     }
	@Override
	public List<Map<String, Object>> getStaticOrderCompany(Map<String, Object> parm) {
		Session  session = sessionFactory.getCurrentSession(); 
		String sql = "select count(*) c_count,b.company_name c_name from t_order a left join t_company b on a.company_id=b.id group by a.company_id  "; 
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
         List<Map<String, Object>> list=query.list();
         logger.info(list);
		return list;
	}
	
	@Override
	public List<TOrder> getmyOrderList(String userid) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from TOrder o where order_scope='"+userid+"'";
	    Query query = session.createQuery(hql);
		List<TOrder> orderList = query.list();
		logger.info(orderList);
		return orderList;
	}

}
