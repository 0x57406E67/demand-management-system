package com.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_user", schema="itsys")
public class TUser{
	private Integer id;
	private String login_name;
	private String login_password;
	private String real_name;
	private TDepart dept;
	private List<TRole> roles;
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="t_user_role",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id")
    )
	public List<TRole> getRoles() {
		return roles;
	}
	public void setRoles(List<TRole> roles) {
		this.roles = roles;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dept_id")
	public TDepart getDept() {
		return dept;
	}
	public void setDept(TDepart dept) {
		this.dept = dept;
	}
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="LOGIN_NAME",length=100)
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	@Column(name="LOGIN_PASSWORD",length=100)
	public String getLogin_password() {
		return login_password;
	}
	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}
	@Column(name="REAL_NAME",length=100)
	public String getReal_name() {
		return real_name;
	}
	
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	
	
	
}