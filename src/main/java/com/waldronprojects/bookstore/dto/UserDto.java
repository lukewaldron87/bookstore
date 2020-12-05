package com.waldronprojects.bookstore.dto;

import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.validation.FieldMatch;
import com.waldronprojects.bookstore.validation.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class UserDto{

	private Long id;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String username;
	
	@NotNull(message = "is required")
	@Size(min = 8, message = "must be at least 8 characters long")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String firstName;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String lastName;
	
	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;
	
	private Collection<Role> roles;
	
	public UserDto() {
	}

	public UserDto(String username, String password, String firstName, String lastName, String email) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public UserDto(String username, String password, String firstName, String lastName, String email,
			Collection<Role> roles) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("UserDto{")
				.append("id=").append(id)
				.append(", username='").append(username).append("'")
				.append(", password='").append("*********").append("'")
				.append(", firstName='").append(firstName).append("'")
				.append(", lastName='").append(lastName).append("'")
				.append(", email='").append(email).append("'")
				.append(", roles=").append(roles).append('}');
		return stringBuilder.toString();
	}

}
