package com.waldronprojects.bookstore.dto;

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

import com.waldronprojects.bookstore.entity.Role;

public class EmployeeDto extends UserDto{

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String department;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String title;
	
	public EmployeeDto() {
		super();
	}

	public EmployeeDto(String username, String password,
					String firstName, String lastName, 
					String email, String department, 
					String title) {

		super(username, password, firstName, lastName, email);
		this.department = department;
		this.title = title;
	}

	public EmployeeDto(String username, String password,
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
		return "Employee [department=" + department + ", title=" + title + "]";
	}
}
