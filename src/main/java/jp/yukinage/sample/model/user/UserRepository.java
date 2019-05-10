package jp.yukinage.sample.model.user;

public interface UserRepository {
    public User findById( UserId userId );
    public UserList listAll();
}
