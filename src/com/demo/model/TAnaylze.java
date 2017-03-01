package com.demo.model;

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
 * TAnaylze entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_anaylze", catalog = "itsys")
public class TAnaylze implements java.io.Serializable {

	// Fields

	private Integer id;
	private TOrder TOrder;
	private String opinion;

	// Constructors

	/** default constructor */
	public TAnaylze() {
	}

	/** full constructor */
	public TAnaylze(TOrder TOrder, String opinion) {
		this.TOrder = TOrder;
		this.opinion = opinion;
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

	@Column(name = "opinion")
	public String getOpinion() {
		return this.opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

}