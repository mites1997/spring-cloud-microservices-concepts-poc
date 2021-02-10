package com.appeveloperblog.photoapp.api.users.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {
	@NotNull(message="firstname cannot be null")
	@Size(min=2,message="firstname not less than 2 characters")
	private String fname;
	private String lname;
	@NotNull(message="password cannot be null")
	@Size(min=8,max=16,message="password must be greater than equals to 8 characters but less than 16 characters")
	private String password;
	@NotNull(message="email cannot be null")
	@Email
	private String email;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
