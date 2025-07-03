package com.example.zikosyoukaiWeb;

import java.util.Date;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class IntroductionsForm {

	private Integer introduction_id;

	private String user_id;

	@NotNull
	private String name;

	@NotNull
	private String kana;

	private String gender;

	private String hobby;

	private String word;

	private Date created_at;

	private Date updated_at;

	public void setIntroduction_id(int id) {
		this.introduction_id = Integer.valueOf(id);
	}

}
