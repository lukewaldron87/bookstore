package com.waldronprojects.bookstore.dto;

import com.waldronprojects.bookstore.entity.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

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
	private String phoneNumber;

	public CustomerDto() {
		super();
	}

	public CustomerDto(String username, String password, String firstName,
					   String lastName, String email, String addressLine1,
					   String addressLine2, String city, String country,
					   String postCode, String phoneNumber) {
		
		super(username, password, firstName, lastName, email);
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.country = country;
		this.postCode = postCode;
		this.phoneNumber = phoneNumber;
	}

	public CustomerDto(String username, String password, String firstName,
					   String lastName, String email, Collection<Role> roles,
					   String addressLine1, String addressLine2, String city,
					   String country, String postCode, String phoneNumber) {
		
		super(username, password, firstName, lastName, email, roles);
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "CustomerDto ["+super.toString()+" addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city
				+ ", country=" + country + ", postCode=" + postCode + ", phoneNumber=" + phoneNumber + "]";
	}
}
