package com.iiht.training.eloan.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
	private Long id;
	@NotNull(message="First Name is not Null")
    @Size(min=3,max=100,message = "First Name must be 3 to 100 characters length")
	private String firstName;
	@NotNull(message="Last Name is not Null")
    @Size(min=3,max=100,message = "Last Name must be 3 to 100 characters length")
	private String lastName;
	@NotNull(message="Email is not Null")
    @Size(min=3,max=100,message = "Email must be 3 to 100 characters length")
	@Email(message="Email should be in proper format")
	private String email;
	@NotNull(message="Mobile number is mandate")
    @Size(min=10,max=10,message = "Mobile number must be ten digits")
	@Pattern(regexp = "[1-9][0-9]{9}",message = "Mobile number must be ten digits")
	private String mobile;
	private String role;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
