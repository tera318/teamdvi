package com.example.zikosyoukaiWeb;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.zikosyoukaiWeb.dao.entity.Introductions;
import com.example.zikosyoukaiWeb.dao.mapper.IntroductionsMapper;

@Service
@Transactional
public class IntroductionsService {

	@Autowired
	IntroductionsMapper introductionsmapper;

	//	public List<Introductions> selectByExample() {
	//		IntroductionsExample introductionsExample = new IntroductionsExample();
	//		return introductionsmapper.selectByExample(introductionsExample);
	//	}

	public void insert(IntroductionsForm introductionsform) {

		Introductions introductions = new Introductions();

		introductions.setName(introductionsform.getName());
		introductions.setKana(introductionsform.getKana());
		introductions.setGender(introductionsform.getGender());
		introductions.setHobby(introductionsform.getHobby());
		introductions.setWord(introductionsform.getWord());
		introductions.setCreated_at(new Date(System.currentTimeMillis()));

		System.out.println("データを登録しました。");
		introductionsmapper.insert(introductions);
	}

}
