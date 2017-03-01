package com.demo.model;


import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import static javax.persistence.GenerationType.IDENTITY;


import javax.persistence.Id;

import javax.persistence.Table;


import org.hibernate.annotations.Cascade;

import org.hibernate.annotations.CascadeType;
/**
 * TDept entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "t_depart", catalog = "itsys")

public class TDepart implements java.io.Serializable {

	
// Fields

	private Integer id;
	
private String deptName;
	
private Integer pid;
	
private Set<TUser> TUsers = new HashSet<TUser>();
	
	
@OneToMany(fetch= FetchType.LAZY)
	
@Cascade(value={CascadeType.SAVE_UPDATE})         //设定级联关系
	
@JoinColumn(name="dept_id") //user表的关联字段
	
public Set<TUser> getTUsers() {
		
return TUsers;
	}

	
public void setTUsers(Set<TUser> tUsers) {
		
TUsers = tUsers;
	
}



// Constructors

	/** default constructor */
	
public TDepart() {
	}

	/** full constructor */
	public TDepart(String deptName, Integer pid) {
		this.deptName = deptName;
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

	@Column(name = "dept_name", length = 200)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "pid")
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}