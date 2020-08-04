package com.cts.assignment.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String username;
	private String name;
	private String password;
	private Date date;

	public User() {
		this.date = new Date();

	}

//	public User(String username, String name, String password) {
//		this.username = username;
//		this.name = name;
//		this.password = password;
//		this.date = new Date();
//	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

//	@Override
//	public String toString() {
//		return "User [username=" + username + ", name=" + name + ", password=" + password + ", date=" + date + "]";
//	}

}
