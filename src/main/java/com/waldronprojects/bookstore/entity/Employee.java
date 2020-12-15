package com.waldronprojects.bookstore.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name="Employee")
public class Employee extends User{

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

	public Employee(String username, String password,
					String firstName, String lastName, 
					String email, String department, 
					String title) {

		super(username, password, firstName, lastName, email);
		this.department = department;
		this.title = title;
	}

	public Employee(String username, String password,
					String firstName, String lastName,
					String email, Collection<Role> roles, 
					String department, String title) {

		super(username, password, firstName, lastName, email, roles);
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
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Employee [").append(super.toString())
					 .append("department=").append(department)
					 .append(", title=").append(title)
					 .append("]");
		return stringBuilder.toString();
	}
}
