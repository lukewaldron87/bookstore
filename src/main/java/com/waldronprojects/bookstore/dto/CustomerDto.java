package com.waldronprojects.bookstore.dto;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.waldronprojects.bookstore.entity.Role;

public class CustomerDto extends UserDto{
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String addressLine1;
	
	private String addressLine2;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String city;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String country;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String postCode;
	
	@NotNull(message = "is required")
	private int phoneNumber;

	public CustomerDto() {
		super();
	}

	public CustomerDto(String userName, String password, String firstName, 
					String lastName, String email, String addressLine1, 
					String addressLine2, String city, String country, 
					String postCode, int phoneNumber) {
		
		super(userName, password, firstName, lastName, email);
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.country = country;
		this.postCode = postCode;
		this.phoneNumber = phoneNumber;
	}

	public CustomerDto(String userName, String password, String firstName, 
					String lastName, String email, Collection<Role> roles, 
					String addressLine1, String addressLine2, String city, 
					String country, String postCode, int phoneNumber) {
		
		super(userName, password, firstName, lastName, email, roles);
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.country = country;
		this.postCode = postCode;
		this.phoneNumber = phoneNumber;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Customer [addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city
				+ ", country=" + country + ", postCode=" + postCode + ", phoneNumber=" + phoneNumber + "]";
	}
}
