package com.example.zikosyoukaiWeb;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.zikosyoukaiWeb.dao.entity.Users;
import com.example.zikosyoukaiWeb.dao.entity.UsersExample;
import com.example.zikosyoukaiWeb.dao.mapper.UsersMapper;

@Service
@Transactional
public class UserService {

	@Autowired
	UsersMapper usersmapper;

	//	public List<Users> selectByExample(UserForm userform) {
	//		UsersExample usersExample = new UsersExample();
	//		return usersmapper.selectByExample(usersExample);
	//	}

	public void insert(UserForm userform) {

		Users users = new Users();

		users.setUser_id(userform.getId());
		users.setEmail(userform.getEmail());
		users.setCreated_at(new Date(System.currentTimeMillis()));

		String password = userform.getPassword();
		String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
		users.setPassword(pw_hash);
		usersmapper.insert(users);
	}

	// ログイン処理のパスワードチェックメソッド
	public boolean password_check(Users users) {
		System.out.println("password_check が呼び出されました。");

		// 1. ユーザーIDでユーザー情報をDBから取得
		UsersExample usersExample = new UsersExample();
		usersExample.createCriteria().andUserIdEqualTo(users.getUser_id());
		List<Users> usersList = usersmapper.selectByExample(usersExample);

		if (usersList.isEmpty()) {
			System.out.println("ユーザーIDが見つかりません。");
			return false; // ユーザーが存在しない
		}

		Users user = usersList.get(0); // 該当ユーザー（通常はユニークなIDなので1件のみ）

		// 2. データベースに保存されているハッシュ化されたパスワードを取得
		String hashedPasswordFromDB = user.getPassword();

		// 3. ユーザーが入力した平文のパスワードと、DBのハッシュ化されたパスワードを比較
		String rawPasswordInput = users.getPassword();

		if (BCrypt.checkpw(rawPasswordInput, hashedPasswordFromDB)) {
			System.out.println("パスワードが一致しました。");
			return true; // 認証成功
		} else {
			System.out.println("パスワードが一致しません。");
			return false; // 認証失敗
		}
	}

}