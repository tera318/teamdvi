package com.example.zikosyoukaiWeb;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.zikosyoukaiWeb.dao.entity.Introductions;
import com.example.zikosyoukaiWeb.dao.mapper.IntroductionsMapper;

@Service
@Transactional
public class IntroductionsService {

	@Autowired
	private IntroductionsMapper introductionsmapper;

	/** 新規登録処理 */
	public void insert(IntroductionsForm form) {
		Introductions entity = convertFormToEntity(form);
		entity.setCreated_at(new Date());
		introductionsmapper.insert(entity);
	}

	/** 一覧取得 */
	public List<Introductions> introductions_date() {
		return introductionsmapper.selectAll();
	}

	/** IDで1件取得 */
	public Introductions findById(int id) {
		return introductionsmapper.findById(id);
	}

	/** 更新処理 */
	public void update(IntroductionsForm form) {
		Introductions entity = convertFormToEntity(form);
		entity.setIntroduction_id((form.getIntroduction_id()));
		entity.setUpdated_at(new Date());
		introductionsmapper.updateByPrimaryKeySelective(entity);
	}

	/** 削除処理 */
	public void delete(int id) {
		introductionsmapper.delete(id);
	}

	/** フォーム → エンティティ変換 */
	private Introductions convertFormToEntity(IntroductionsForm form) {
		Introductions entity = new Introductions();

		if (form.getIntroduction_id() != null) {
			entity.setIntroduction_id(form.getIntroduction_id());
		}

		entity.setName(form.getName());
		entity.setKana(form.getKana());
		entity.setGender(form.getGender());

		entity.setHobby(String.join(",", form.getHobby())); // hobby

		entity.setWord(form.getWord());
		introductionsmapper.insert(entity);
		return entity;
	}
}
