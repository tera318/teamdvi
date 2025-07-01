package com.example.zikosyoukaiWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IntroductionsController {

	public IntroductionsController() {
	}

	@Autowired
	IntroductionsService introductionsService;

	/* 入力画面 */
	@GetMapping("/introductions")
	public ModelAndView inputG(ModelAndView inputmodel) {
		inputmodel.setViewName("input");
		inputmodel.addObject("introductionsform", new IntroductionsForm());
		return inputmodel;
	}

	@PostMapping("/introductions")
	public ModelAndView inputP(ModelAndView mav, IntroductionsForm introductionsform) {

		System.out.println("入力画面へ出力しました。");

		mav.setViewName("confirm");
		mav.addObject("introductionsform", introductionsform);

		System.out.println("入力画面へ出力しました。");

		return mav;
	}

	/* 確認画面 */
	@GetMapping("/introductions/confirm")
	public ModelAndView confirmG(ModelAndView confirmmav) {
		confirmmav.setViewName("confirm");
		confirmmav.addObject("introductionsform", new IntroductionsForm());
		return confirmmav;
	}

	@PostMapping("/introductions/confirm")
	public ModelAndView confirmP(ModelAndView mav, IntroductionsForm introductionsform) {
		System.out.println("確認画面へ出力しました。");
		mav.setViewName("confirmfinish");
		mav.addObject("introductionsform", introductionsform);
		System.out.println("確認画面へ出力しました。");
		return mav;
	}

	/* 登録完了画面 */
	@GetMapping("/introductions/finish")
	public ModelAndView registerfinishG() {
		ModelAndView registerfinishmodel = new ModelAndView();
		registerfinishmodel.setViewName("registerfinish");
		return registerfinishmodel;
	}

	/* 一覧画面 */
	@GetMapping("/introductions/list")
	public ModelAndView listG() {
		ModelAndView listmodel = new ModelAndView();
		listmodel.setViewName("list");
		return listmodel;
	}

	@PostMapping("/introductions/list")
	public String listP() {
		return "list";
	}

	/* 詳細画面 */
	@GetMapping("/introductions/detail")
	public ModelAndView detailG() {
		ModelAndView detailmodel = new ModelAndView();
		detailmodel.setViewName("detail");
		return detailmodel;
	}

	@PostMapping("/introductions/detail")
	public String detailP() {
		return "detail";
	}

}
