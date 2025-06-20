package com.example.zikosyoukaiWeb;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserForm {

	@NotNull
	@Size(min = 3, max = 10)
	private String id;

	@Email
	private String email;

	@NotNull
	@Size(min = 3, max = 10)
	private String password;

	private Integer created_at;

	private String team_name;
}
