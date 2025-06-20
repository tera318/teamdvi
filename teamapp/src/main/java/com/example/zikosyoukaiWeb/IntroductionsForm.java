package com.example.zikosyoukaiWeb;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data

public class IntroductionsForm {

	@NotNull
	private String name;

	@NotNull
	private String kana;

	private String hobyy;

}
