package jp.yukinage.sample.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.yukinage.sample.exception.ResourceNotFoundException;
import jp.yukinage.sample.model.user.User;
import jp.yukinage.sample.model.user.UserId;
import jp.yukinage.sample.model.user.UserList;
import jp.yukinage.sample.model.user.UserName;
import jp.yukinage.sample.model.user.UserRepository;
import jp.yukinage.sample.service.user.UserService;
import jp.yukinage.sample.service.user.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Rule
    public ExpectedException exception = ExpectedException.none();

	@InjectMocks
	private UserService service = new UserServiceImpl();

	@Mock
	private UserRepository repository;

	private UserList userList;

	@Before
	public void before()
	{
		User user1 = new User( new UserId(1L), new UserName("山田 ひろ子"));
		User user2 = new User( new UserId(2L), new UserName("清水 雄太郎"));

		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		userList = new UserList(users);
	}

	@Test
	public void findById_ユーザー取得()
	{
		UserId userId = new UserId(1L);
		// 期待値
		User expected = new User( userId, new UserName("山田 ひろ子"));

		when(repository.findById(userId)).thenReturn(expected);

		// 実行結果
		User actual = service.findById(userId);

		// nullでないことの確認
		assertThat(actual).isNotNull();

		// 期待値と実際値が等価であることの確認
		assertThat(actual).isEqualTo(expected);

		// リポジトリメソッドの呼び出しが1回であることの確認
        verify(this.repository, times(1)).findById(userId);
	}

	@Test
	public void findById_存在しないユーザーの取得()
	{
		UserId userId = new UserId( 9999L );
		when(repository.findById(userId)).thenReturn(null);
		// 期待値はResourceNotFoundException
        exception.expect(ResourceNotFoundException.class);

		// 実行結果
		service.findById( userId );

	}

	@Test
	public void listAll_ユーザーリスト取得()
	{
		when(repository.listAll()).thenReturn(userList);

		// 実行結果
		UserList actual = service.listAll();

		// nullでないことの確認
		assertThat(actual).isNotNull();

		// 期待値と実際値が等価であることの確認
		assertThat(actual).isEqualTo(userList);

		// リポジトリメソッドの呼び出しが1回であることの確認
        verify(this.repository, times(1)).listAll();
	}
}
