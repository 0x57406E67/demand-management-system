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
 * TRoleResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_role_resource", catalog = "itsys")
public class TRoleResource implements java.io.Serializable {

	// Fields

	private Integer id;
	private TRole TRole;
	private TResource TResource;

	// Constructors

	/** default constructor */
	public TRoleResource() {
	}

	/** full constructor */
	public TRoleResource(TRole TRole, TResource TResource) {
		this.TRole = TRole;
		this.TResource = TResource;
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
	@JoinColumn(name = "role_id")
	public TRole getTRole() {
		return this.TRole;
	}

	public void setTRole(TRole TRole) {
		this.TRole = TRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_id")
	public TResource getTResource() {
		return this.TResource;
	}

	public void setTResource(TResource TResource) {
		this.TResource = TResource;
	}

}