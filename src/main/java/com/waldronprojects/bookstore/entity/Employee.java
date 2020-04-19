package com.waldronprojects.bookstore.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name="Employee")
public class Employee extends User{

	/*@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="user_id")
	private int userId;*/
	
	@Column(name="department")
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String department;
	
	@Column(name="title")
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String title;
	
	public Employee() {
		super();
	}

	public Employee(String userName, String password,
					String firstName, String lastName, 
					String email, String department, 
					String title) {

		super(userName, password, firstName, lastName, email);
		this.department = department;
		this.title = title;
	}

	public Employee(String userName, String password,
					String firstName, String lastName,
					String email, Collection<Role> roles, 
					String department, String title) {

		super(userName, password, firstName, lastName, email, roles);
		this.department = department;
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Employee [department=" + department + ", title=" + title + "]";
	}
}
