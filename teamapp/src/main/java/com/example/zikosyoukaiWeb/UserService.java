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

	//	private String selectUsers() {
	//		String user = "SELECT * FROM users WHERE user_id = ?";
	//		return selectUsers(user);
	//	}

	public List<Users> selectByExample(UserForm userform) {

		UsersExample usersExample = new UsersExample();

		//		String selectuser = selectUsers();

		//		usersmapper.equals(userform, selectuser);

		return usersmapper.selectByExample(usersExample);
	}

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

	//	public static boolean checkpw(String ) {
	//		
	//		return;
	//	}

}
