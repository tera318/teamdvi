package com.example.zikosyoukaiWeb;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.zikosyoukaiWeb.dao.entity.Users;

@Controller
public class ToppageController {

	@Autowired
	UserService userService;

	/* Top画面 */
	@GetMapping("/home")
	public ModelAndView toppageG(ModelAndView topmodel) {
		topmodel.setViewName("toppage");
		return topmodel;
	}

	/* ログイン画面 */
	@GetMapping("/home/login")
	public ModelAndView loginpaeG(ModelAndView loginmodel) {
		loginmodel.setViewName("login");
		loginmodel.addObject("userform", new UserForm());
		return loginmodel;
	}

	@PostMapping("/home/login")
	public String loginpaeP(@ModelAttribute @Valid Users users, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "login";
		}

		if (userService.password_check(users)) {
			System.out.println("ログイン成功");
		} else {
			System.err.println("ログイン失敗");
			return "loginfailed";
		}

		return "redirect:/home";

	}

	/* 新規登録 */
	@GetMapping("/users")
	public ModelAndView registerG(ModelAndView registermodel) {
		registermodel.setViewName("register");
		registermodel.addObject("userform", new UserForm());
		return registermodel;
	}

	@PostMapping("/users")
	public String registerP(@ModelAttribute @Valid UserForm userform, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "register";
		}

		userService.insert(userform);
		return "redirect:/home/login";
	}

	/*必要な処理
	 * 
	 * ログイン画面 /loginpage
	 * ・ID(テキストボックス)、PW(テキストボックス)
	 * ・登録したID/PWをDBから取得して問題なければTOP画面に遷移、不適切ならバリデーション処理
	 * ・新規会員登録画面への遷移URL追加
	 * 
	 * 新規登録画面 /register 
	 * ・UserID,mailaddress,password(全てテキストボックス)
	 * ・UserID,mailaddress,passwordを入力し、正しく入力されればDBにその内容を追加(INSERT)
	 * ・不適切な内容であればバリデーション処理
	 * ・登録完了後、ポップアップ表示
	 * 
	 * Top画面 /
	 * ・自己紹介登録と自己紹介登録者一覧に遷移
	 * ・ログイン画面へのリダイレクト処理
	 * ・ダイレクトアクセス対策処理
	 * 
	 * 入力画面機能 /nyuuryoku
	 * ・名前、ふりがな、性別(ラジオボタン)、趣味(チェックボックス)、一言の入力項目に
	 * 　入力された内容をセッションで確認画面へ受け渡し
	 * ・セッションの有効期限は1時間(仮)
	 * ・戻る(Top画面)、次へ(確認画面)への遷移
	 * ・ダイレクトアクセス対策処理
	 * 
	 * 確認画面 /kakunin
	 * ・入力画面で入力した内容をセッションで受け渡しして表示
	 * ・完了ボタン押下後にDBへ内容を登録
	 * ・戻るボタンで入力画面へ遷移
	 * ・セッションで内容を受け渡し
	 * ・ダイレクトアクセス対策処理
	 * 
	 * 登録完了画面 /kanryou
	 * ・DBに保存された情報を表示
	 * ・管理番号(ID)を追加で表示
	 * ・ダイレクトアクセス対策処理
	 * 
	 * 一覧画面 /ichiran
	 * ・登録されたID(管理番号)と名前を全て表示してIDを押すと詳細画面へ遷移
	 * ・表示の順番は登録が新しい順
	 * ・ダイレクトアクセス対策処理
	 * 
	 * 詳細画面 /syousai
	 * ・一覧画面でクリックされたユーザーの管理番号(ID)、名前、ふりがな、性別、趣味、一言を表示
	 * ・編集ボタンで入力画面へ遷移
	 * ・削除ボタンで確認用ポップアップ表示、「OK」でレコードをDBから削除、「CANCEL」でポップアップを閉じる
	 * ・ダイレクトアクセス対策処理
	 * 
	 * バリデーション
	 * ・新規登録画面、ログイン画面、入力画面にバリデーションを追加
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}