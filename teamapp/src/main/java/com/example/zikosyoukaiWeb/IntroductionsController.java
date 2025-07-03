package com.example.zikosyoukaiWeb;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.zikosyoukaiWeb.dao.entity.Introductions;

@Controller
public class IntroductionsController {

	@Autowired
	private IntroductionsService introductionsService;

	/** 入力画面表示（新規登録） */
	@GetMapping("/introductions")
	public ModelAndView showInputForm() {
		ModelAndView mav = new ModelAndView("input");
		mav.addObject("introductionsform", new IntroductionsForm());
		return mav;
	}

	/** 入力内容確認 */
	@PostMapping("/introductions/confirm")
	public ModelAndView confirm(@ModelAttribute IntroductionsForm form) {
		ModelAndView mav = new ModelAndView("confirm");
		mav.addObject("introductionsform", form);
		return mav;
	}

	/** 確認画面から登録 or 更新 */
	@PostMapping("/introductions/confirm/submit")
	public String submit(@ModelAttribute IntroductionsForm form, RedirectAttributes redirectAttributes) {
		if (form.getIntroduction_id() == null) {
			introductionsService.insert(form);
		} else {
			introductionsService.update(form);
		}
		redirectAttributes.addFlashAttribute("introductionsform", form);
		return "redirect:/introductions/finish";
	}

	/** 登録完了画面 */
	@GetMapping("/introductions/finish")
	public String finish() {
		return "confirmfinish";
	}

	/** 一覧画面 */
	@GetMapping("/introductions/list")
	public String showList(Model model) {
		List<Introductions> introductionsList = introductionsService.introductions_date();
		model.addAttribute("introductionsList", introductionsList);
		return "list";
	}

	/** 詳細画面 */
	@GetMapping("/introductions/detail/{id}")
	public String showDetail(@PathVariable int id, Model model) {
		Introductions intro = introductionsService.findById(id);
		model.addAttribute("introduction", intro);
		return "detail";
	}

	/** 編集画面表示 */

	@GetMapping("/introductions/edit/{id}")
	public ModelAndView edit(@PathVariable("id") int id) {
		Introductions intro = introductionsService.findById(id);

		IntroductionsForm form = new IntroductionsForm();

		form.setIntroduction_id(intro.getIntroduction_id());
		form.setName(intro.getName());
		form.setKana(intro.getKana());
		form.setGender(intro.getGender());

		if (intro.getHobby() != null && !intro.getHobby().isEmpty()) {
			form.setHobby(Arrays.asList(intro.getHobby().split(",")));
		}

		form.setWord(intro.getWord());

		ModelAndView mav = new ModelAndView("input");
		mav.addObject("introductionsform", form);
		return mav;
	}

	/** 戻る（確認画面 → 入力画面） */
	@PostMapping("/introductions/back")
	public String back(@ModelAttribute IntroductionsForm form, Model model) {
		model.addAttribute("introductionsform", form);
		return "input";
	}

	/** 削除処理 */
	@GetMapping("/introductions/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		introductionsService.delete(id);
		return "redirect:/introductions/list";
	}

}
