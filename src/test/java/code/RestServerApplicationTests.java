package code;

import code.model.User;
import code.model.WorkAroundPair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestServerApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Test
	public void userTest()
	{
		String login1 = "User";
		String login2 = "{4652dchj";
		String login3 = "4652dchj";
		try
		{
			User.loginIsVaild(login2);
		}
		catch (Exception e)
		{
			try
			{
				User.loginIsVaild(login1);
				User.loginIsVaild(login3);
				System.out.println("Correct");
			}
			catch (Exception e1)
			{
				System.out.println("Incorrect");
			}
		}
		User user = new User(login1, "Password");
		user.addMessage("Message1");
		user.addMessage("Message2");
		user.addMessage("Message3");
		try
		{
			user.deleteMessage(-8);
		}
		catch (Exception ex)
		{
			try
			{
				user.deleteMessage(9);
			}
			catch (Exception ex1)
			{
				try
				{
					user.deleteMessage(1);
				}
				catch (Exception ex2)
				{
					System.out.println("Something wrong");
				}
			}
		}
		assertEquals("+Message1+Message3", user.getMessages().stream()
				.map(WorkAroundPair::getMsg)
				.reduce("", (a, b) -> a+"+"+b));
		User user1 = new User(login1, "sdds");
		User user2 = new User(login2, "sdds");
		assertEquals(user, user1);
		assertNotEquals(user, user2);
	}

	@Test
	public void userStorageTest()
	{

	}
}
