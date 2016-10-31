package ru.urfu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterApplicationTests
{

	@Test
	public void contextLoads() {}

	@Test
	public void checkMessageStorageCorrectness()
	{
		MessageStorage messt = new MessageStorage();
		messt.addMessage("Wow!");
		String htmlStr =
				"<html>" +
				"   <link rel=\"stylesheet\" type=\"text/css\" href=\"/twitter.css\"/>" +
				"   <body>" +
				"       <h1>twitter</h1>" +
				"       This is your twitter application" +
				"       <ul class=\"messages\">" +
						"<li>" + "Wow!" + "</li>" +
				"       </ul>" +
				"   </body>" +
				"</html>";
		List<String> listIds = messt.renderMessageIds("Wow!");
		assertEquals(htmlStr, messt.renderMessageById(listIds.get(0)));
		messt.deleteMessage("00000000001");
		messt.deleteMessage("00000000002");
		assertEquals(htmlStr, messt.renderAllMessages());
	}

}
