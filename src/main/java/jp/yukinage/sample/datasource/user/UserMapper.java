package jp.yukinage.sample.datasource.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.yukinage.sample.model.user.User;
import jp.yukinage.sample.model.user.UserId;

@Mapper
public interface UserMapper {
    public User findById(@Param("userId") UserId userId);
    public List<User> listAll();
}
