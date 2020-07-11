package com.waldronprojects.bookstore.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name="customer")
public class Customer extends User{

	@Column(name="address_line_1")
	private String addressLine1;
	
	@Column(name="address_line_2")
	private String addressLine2;
	
	@Column(name="city")
	private String city;
	
	@Column(name="country")
	private String country;
	
	@Column(name="post_code")
	private String postCode;
	
	@Column(name="phone_number")
	private int phoneNumber;

	public Customer() {
		super();
	}

	public Customer(String username, String password, String firstName, 
					String lastName, String email, String addressLine1, 
					String addressLine2, String city, String country, 
					String postCode, int phoneNumber) {
		
		super(username, password, firstName, lastName, email);
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.country = country;
		this.postCode = postCode;
		this.phoneNumber = phoneNumber;
	}

	public Customer(String username, String password, String firstName, 
					String lastName, String email, Collection<Role> roles, 
					String addressLine1, String addressLine2, String city, 
					String country, String postCode, int phoneNumber) {
		
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

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Customer [" + super.toString() + "  addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city
				+ ", country=" + country + ", postCode=" + postCode + ", phoneNumber=" + phoneNumber +  "]";
	}
}
