package jp.yukinage.sample.datasource.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.yukinage.sample.model.user.User;
import jp.yukinage.sample.model.user.UserId;
import jp.yukinage.sample.model.user.UserList;
import jp.yukinage.sample.model.user.UserRepository;

@Repository
public class UserDataSource implements UserRepository {

	@Autowired
	private UserMapper mapper;

	@Override
	public User findById(UserId userId) {
		return mapper.findById(userId);
	}

	@Override
	public UserList listAll() {
		List<User> users = mapper.listAll();

		if (users == null)
			return new UserList();

		return new UserList(users);
	}
}
