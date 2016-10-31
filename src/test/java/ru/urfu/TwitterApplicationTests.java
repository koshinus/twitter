package ru.urfu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.AbstractMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterApplicationTests
{

	@Test
	public void contextLoads() {}

	@Test
	public void checkMessageCorrectness()
	{
		Message mes = new Message("Hello, world!");
		assertEquals(mes, new Message("Hello, world!"));
		assertNotEquals(mes, new Message("Hello!"));
	}

	@Test
	public void checkMessageStorageCorrectness()
	{
		MessageStorage messt = new MessageStorage();
		String str = "Pops";
		AbstractMap.SimpleImmutableEntry<String, Message> entry1 =
				new AbstractMap.SimpleImmutableEntry<>("00000000003", new Message(str));
		AbstractMap.SimpleImmutableEntry<String, Message> entry2 =
				new AbstractMap.SimpleImmutableEntry<>("00000001", new Message(str));
		AbstractMap.SimpleImmutableEntry<String, Message> entry3 =
				new AbstractMap.SimpleImmutableEntry<>("00000000001", new Message(str));
		messt.addMessage("Wow!");
		//messt.printMessageStore();
		assertEquals("00000000003", messt.addMessage(entry1));
		assertNotEquals("00000001", messt.addMessage(entry2));
		assertNotEquals("00000000001", messt.addMessage(entry3));
		//messt.printMessageStore();
		String htmlStr =
				"<html>" +
				"   <link rel=\"stylesheet\" type=\"text/css\" href=\"/twitter.css\"/>" +
				"   <body>" +
				"       <h1>twitter</h1>" +
				"       This is your twitter application" +
				"       <ul class=\"messages\">" +
						//"<li>" + "Wow!" + "</li>" +
						"<li>" + "Pops" + "</li>" +
				"       </ul>" +
				"   </body>" +
				"</html>";
		assertEquals(htmlStr, messt.renderMessageById("00000000003"));
		messt.deleteMessage("00000000001");
		messt.deleteMessage("00000000002");
		//messt.printMessageStore();
		String htmlStr1 =
				"<html>" +
						"   <link rel=\"stylesheet\" type=\"text/css\" href=\"/twitter.css\"/>" +
						"   <body>" +
						"       <h1>twitter</h1>" +
						"       This is your twitter application" +
						"       <ul class=\"messages\">" +
								"<li>" + "Wow!" + "</li>" +
								"<li>" + "Pops" + "</li>" +
								"<li>" + "Pops" + "</li>" +
								"<li>" + "Pops" + "</li>" +
						"       </ul>" +
						"   </body>" +
						"</html>";
		assertEquals(htmlStr1, messt.renderAllMessages());
		/*
		try
		{ messt.addMessage(new AbstractMap.SimpleImmutableEntry<String, Message>("00000000001", new Message(str))); }
		catch (Exception ex)
		{ System.out.println(ex.getLocalizedMessage()); }
		finally
		{ messt.addMessage(str); }
		try
		{ messt.addMessage(new AbstractMap.SimpleImmutableEntry<String, Message>("00000001", new Message(str))); }
		catch (Exception ex)
		{ System.out.println(ex.getLocalizedMessage()); }
		finally
		{ messt.addMessage(str); }
		*/
	}

}
