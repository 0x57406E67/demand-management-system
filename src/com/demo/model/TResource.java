package com.demo.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_resource", catalog = "itsys")
public class TResource implements java.io.Serializable {

	// Fields

	private Integer id;
	private String resName;
	private String url;
	private Integer pid;
	private List<TRole> roles;

	// Constructors
	@ManyToMany(mappedBy="resources",cascade=CascadeType.ALL)
	public List<TRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TRole> roles) {
		this.roles = roles;
	}

	/** default constructor */
	public TResource() {
	}

	/** full constructor */
	public TResource(String resName, String url, Integer pid) {
		this.resName = resName;
		this.url = url;
		this.pid = pid;
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

	@Column(name = "res_name", length = 100)
	public String getResName() {
		return this.resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	@Column(name = "url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "pid")
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}