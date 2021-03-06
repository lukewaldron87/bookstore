package com.waldronprojects.bookstore.entity;

import javax.persistence.*;
import java.util.Collection;

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
	private String phoneNumber;

	public Customer() {
		super();
	}

	public Customer(String username, String password, String firstName,
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

	public Customer(String username, String password, String firstName,
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
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Customer [").append(super.toString())
				.append("  addressLine1=").append(addressLine1)
				.append(", addressLine2=").append(addressLine2)
				.append(", city=").append(city)
				.append(", country=").append(country)
				.append(", postCode=").append(postCode)
				.append(", phoneNumber=").append(phoneNumber)
				.append( "]");
		return stringBuilder.toString();
	}
}
