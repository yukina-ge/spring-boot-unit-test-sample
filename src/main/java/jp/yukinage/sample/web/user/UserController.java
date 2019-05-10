package jp.yukinage.sample.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.yukinage.sample.model.user.User;
import jp.yukinage.sample.model.user.UserId;
import jp.yukinage.sample.model.user.UserList;
import jp.yukinage.sample.model.user.UserRepository;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserRepository repository;

	@GetMapping
	public UserList users() {
		UserList userList = repository.listAll();
		return userList;
	}
	@GetMapping("{userId:\\d+}")
	public User detail(@PathVariable Long userId ) {
		User user = repository.findById( new UserId(userId));
		return user;
	}
}
