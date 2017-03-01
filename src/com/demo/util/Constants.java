package com.demo.util;

public class Constants {
	//登录用户
	public static final String CURRENT_LOGIN_USER="login_user";
	//登录用户的权限集合
	public static final String PERMITS="login_permits";
	/**
	 * 工单状态
	 */
	public static final String ADD_ORDER="0";//新建需求
	public static final String ORDER_ANALYSE="1";//需求分析
	public static final String ORDER_CONFIRM="2";//需求确认
	public static final String ORDER_AUDIT="3";//需求审核
	public static final String ORDER_DEVELOP="4";//执行开发
	
	/**
	 * 附件名称
	 */
	public static final String ATTACH_ORDER_CONFIRM="attach_order_confirm";//需求确认表
	public static final String ATTACH_WORK_PREDICT="attach_work_predict";//工作量预估表
	/**
	 * 角色
	 */
	public static final String DEMAND_DRAFT_MAN="1"; //需求起草人    大写：ctrl+shift+x
	public static final String DEVELOP_MAN="2"; //开发负责人
	public static final String CAMPANY_MANAGER="3"; //厂家项目经理
	public static final String DEMAND_ADMIN_MAN="4"; //部门需求管理员

}
