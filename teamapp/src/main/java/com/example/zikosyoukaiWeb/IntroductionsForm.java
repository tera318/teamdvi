package com.example.zikosyoukaiWeb;

import java.util.Date;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data

public class IntroductionsForm {

	private String introduction_id;

	private String user_id;

	@NotNull
	private String name;

	@NotNull
	private String kana;

	private String gender;

	private String hobby;

	private String word;

	private Integer created_at;

	private Date updated_at;

}
