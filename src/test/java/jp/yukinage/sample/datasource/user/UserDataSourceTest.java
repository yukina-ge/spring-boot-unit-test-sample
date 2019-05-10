package jp.yukinage.sample.datasource.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import jp.yukinage.sample.model.user.User;
import jp.yukinage.sample.model.user.UserId;
import jp.yukinage.sample.model.user.UserList;
import jp.yukinage.sample.model.user.UserName;
import jp.yukinage.sample.model.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = {
	"INSERT INTO \"user\".users ( name, kana ) VALUES('山田 ひろ子', 'ヤマダ ヒロコ')",
	"INSERT INTO \"user\".users ( name, kana ) VALUES('清水 雄太郎', 'シミズ ユウタロウ')",
})
public class UserDataSourceTest {

	@Autowired
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
		// 実行結果
		User actual = repository.findById(userId);

		// nullでないことの確認
		assertThat(actual).isNotNull();

		// 期待値と実際値が等価であることの確認
		// 注意：オブジェクトのequalsを正しく実装する必要あり
		// このサンプルではlombokの@EqualsAndHashCodeを使用
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void findById_存在しないユーザーの取得()
	{
		UserId userId = new UserId( 9999L );
		// 実行結果
		User actual = repository.findById( userId );

		// nullであることの確認
		assertNull( "actual is not null", actual );
	}

	@Test
	public void listAll_ユーザーリスト取得()
	{
		// 実行結果
		UserList actual = repository.listAll();

		// nullでないことの確認
		assertThat(actual).isNotNull();

		// 期待値と実際値が等価であることの確認
		// 注意：オブジェクトのequalsを正しく実装する必要あり
		// このサンプルではlombokの@EqualsAndHashCodeを使用
		assertThat(actual).isEqualTo(userList);
	}
}
