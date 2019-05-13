package jp.yukinage.sample.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.yukinage.sample.exception.ResourceNotFoundException;
import jp.yukinage.sample.model.user.User;
import jp.yukinage.sample.model.user.UserId;
import jp.yukinage.sample.model.user.UserList;
import jp.yukinage.sample.model.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public User findById(UserId userId) {
		User user =repository.findById(userId);
		if( user == null )
			throw new ResourceNotFoundException();
		return user;
	}

	@Override
	public UserList listAll() {
		UserList userList =repository.listAll();
		return userList;
	}

}
