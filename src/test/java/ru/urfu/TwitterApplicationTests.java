package ru.urfu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterApplicationTests
{

	@Test
	public void contextLoads()
	{

	}

	@Test
	public void checkMessageCorrectness()
	{
		try
		{
			Message mes = new Message("1234567890k", "Hello, world!");
			assertEquals(mes, new Message("1234567890k", "Hello, world!"));
			assertNotEquals(mes, new Message("1234567890d", "Hello, world!"));
			assertNotEquals(mes, new Message("1234567890k", "Hello!"));
		}
		catch (Exception ex)
		{
			System.out.println(ex.getLocalizedMessage());
		}
	}

}
