package jp.yukinage.sample.model.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    Validator validator;

	@Test
	public void user_正常()
	{
		User user = new User(new UserId(1L), new UserName("田中 太郎"));
	    BindingResult bindingResult = new BindException(user, "user");
		validator.validate(user, bindingResult);
		// エラーがないこと
        assertThat(bindingResult.getErrorCount()).isEqualTo(0);
	}

	@Test
	public void user_ユーザー名が空()
	{
		User user = new User(new UserId(1L), new UserName(""));
	    BindingResult bindingResult = new BindException(user, "user");
		validator.validate(user, bindingResult);
		// エラーが1件あること
        assertThat(bindingResult.getErrorCount()).isEqualTo(1);
        // エラーメッセージが正しいこと
        assertThat(bindingResult.getFieldError("name.value").getDefaultMessage()).isEqualTo("must not be empty");
	}

	@Test
	public void user_ユーザー名の桁閾値()
	{
		User user = new User(new UserId(1L), new UserName("12345678901234567890123456789012345678901234567890"));
	    BindingResult bindingResult = new BindException(user, "user");
		validator.validate(user, bindingResult);
		// エラーがないこと
        assertThat(bindingResult.getErrorCount()).isEqualTo(0);
	}

	@Test
	public void user_ユーザー名の桁あふれ()
	{
		User user = new User(new UserId(1L), new UserName("123456789012345678901234567890123456789012345678901"));
	    BindingResult bindingResult = new BindException(user, "user");
		validator.validate(user, bindingResult);
		// エラーが1件あること
        assertThat(bindingResult.getErrorCount()).isEqualTo(1);
        // エラーメッセージが正しいこと
        assertThat(bindingResult.getFieldError("name.value").getDefaultMessage()).isEqualTo("size must be between 0 and 50");
	}
}

