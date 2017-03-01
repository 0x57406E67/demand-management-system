package com.demo.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TConfirm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_confirm", catalog = "itsys")
public class TConfirm implements java.io.Serializable {

	// Fields

	private Integer id;
	private TOrder TOrder;
	private Date predictTime;
	private String preWork;
	private String initWork;

	// Constructors

	/** default constructor */
	public TConfirm() {
	}

	/** full constructor */
	public TConfirm(TOrder TOrder, Date predictTime, String preWork,
			String initWork) {
		this.TOrder = TOrder;
		this.predictTime = predictTime;
		this.preWork = preWork;
		this.initWork = initWork;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public TOrder getTOrder() {
		return this.TOrder;
	}

	public void setTOrder(TOrder TOrder) {
		this.TOrder = TOrder;
	}

	@Column(name = "predict_time", length = 19)
	public Date getPredictTime() {
		return this.predictTime;
	}

	public void setPredictTime(Date predictTime) {
		this.predictTime = predictTime;
	}

	@Column(name = "pre_work", length = 10)
	public String getPreWork() {
		return this.preWork;
	}

	public void setPreWork(String preWork) {
		this.preWork = preWork;
	}

	@Column(name = "init_work", length = 10)
	public String getInitWork() {
		return this.initWork;
	}

	public void setInitWork(String initWork) {
		this.initWork = initWork;
	}

}