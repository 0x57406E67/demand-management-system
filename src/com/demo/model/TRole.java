package com.demo.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_role", catalog = "itsys")
public class TRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private String roleName;
	private List<TUser> users ;
	private List<TResource> resources;

	// Constructors
	@ManyToMany(mappedBy="roles",cascade=CascadeType.ALL)
	public List<TUser> getUsers() {
		return users;
	}

	public void setUsers(List<TUser> users) {
		this.users = users;
	}
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="t_role_resource",
            joinColumns=@JoinColumn(name="role_id"),
            inverseJoinColumns=@JoinColumn(name="resource_id")
    )
	public List<TResource> getResources() {
		return resources;
	}

	public void setResources(List<TResource> resources) {
		this.resources = resources;
	}

	/** default constructor */
	public TRole() {
	}

	/** full constructor */
	public TRole(String roleName) {
		this.roleName = roleName;
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

	@Column(name = "role_name", length = 50)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	

}