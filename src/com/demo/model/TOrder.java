package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_order", catalog = "itsys")
public class TOrder implements java.io.Serializable {

	// Fields

	private Integer id;
	private String orderName;
	private String sysNo;
	private String orderScope;
	private String oaNo;
	private Integer sysApp;//褰掑睘绯荤粺
	private String orderDept;
	private String orderDetail;
	private String orderApplyMan;
	private String developMan;
	private String date;


	//private Integer companyId;//鎵ц鍘傚晢
	private TCompany company;// 鎵ц鍘傚晢    锛堥渶姹�n)------銆嬪巶鍟�1)锛�	private String orderDetail;
	private String status;
	private Integer orderCreateMan;
	
	/******鍐椾綑瀛楁*********/
	private int company_id;
	private String status_name;





	



	/** default constructor */
	public TOrder() {
	}



	public TOrder(String orderName, String sysNo, String orderScope,
			String oaNo, Integer sysApp, String orderDept,
			String orderApplyMan, String developMan, TCompany company,
			String orderDetail, String status, Integer orderCreateMan) {
		super();
		this.orderName = orderName;
		this.sysNo = sysNo;
		this.orderScope = orderScope;
		this.oaNo = oaNo;
		this.sysApp = sysApp;
		this.orderDept = orderDept;
		this.orderApplyMan = orderApplyMan;
		this.developMan = developMan;
		this.company = company;
		this.orderDetail = orderDetail;
		this.status = status;
		this.orderCreateMan = orderCreateMan;
	}



	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="date",length=10)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@Column(name = "order_name", length = 500)
	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	@Column(name = "sys_no", length = 50)
	public String getSysNo() {
		return this.sysNo;
	}

	public void setSysNo(String sysNo) {
		this.sysNo = sysNo;
	}

	@Column(name = "order_scope", length = 2)
	public String getOrderScope() {
		return this.orderScope;
	}

	public void setOrderScope(String orderScope) {
		this.orderScope = orderScope;
	}

	@Column(name = "oa_no", length = 50)
	public String getOaNo() {
		return this.oaNo;
	}

	public void setOaNo(String oaNo) {
		this.oaNo = oaNo;
	}

	@Column(name = "sys_app")
	public Integer getSysApp() {
		return this.sysApp;
	}

	public void setSysApp(Integer sysApp) {
		this.sysApp = sysApp;
	}

	@Column(name = "order_dept", length = 200)
	public String getOrderDept() {
		return this.orderDept;
	}

	public void setOrderDept(String orderDept) {
		this.orderDept = orderDept;
	}

	@Column(name = "order_apply_man", length = 200)
	public String getOrderApplyMan() {
		return this.orderApplyMan;
	}

	public void setOrderApplyMan(String orderApplyMan) {
		this.orderApplyMan = orderApplyMan;
	}

	@Column(name = "develop_man", length = 200)
	public String getDevelopMan() {
		return this.developMan;
	}

	public void setDevelopMan(String developMan) {
		this.developMan = developMan;
	}

	@Column(name = "order_detail", length = 2000)
	public String getOrderDetail() {
		return this.orderDetail;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	@Column(name = "status", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "order_create_man")
	public Integer getOrderCreateMan() {
		return this.orderCreateMan;
	}

	public void setOrderCreateMan(Integer orderCreateMan) {
		this.orderCreateMan = orderCreateMan;
	}
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="company_id")
	public TCompany getCompany() {
		return company;
	}

	public void setCompany(TCompany company) {
		this.company = company;
	}
	// Constructors
	@Transient
	public int getCompany_id() {
		return company_id;
	}



	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	@Transient
	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

}