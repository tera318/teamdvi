package com.example.zikosyoukaiWeb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<Users> selectByExample() {
		UsersExample usersExample = new UsersExample();
		return usersmapper.selectByExample(usersExample);
	}

	public void insert(Users users) {
		usersmapper.insert(users);
	}

}
