package jp.yukinage.sample.service.user;

import jp.yukinage.sample.model.user.User;
import jp.yukinage.sample.model.user.UserId;
import jp.yukinage.sample.model.user.UserList;

public interface UserService {
    public User findById( UserId userId );
    public UserList listAll();
}
