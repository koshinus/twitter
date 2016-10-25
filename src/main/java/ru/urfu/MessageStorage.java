package ru.urfu;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

/**
 * @author aarkaev
 * @since 08.08.2016
 */
@RestController
public class MessageStorage
{
    private static final Map<String, Message> _messages = new HashMap<>();

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    void addMessages(@RequestParam("mes") String mes)
    {
        char _id[] = new char[11];
        Message check = _messages.get(new String(_id));
        while (check != null)
        {
            String alphabet =
                    "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            Random rand = new Random();
            for (int i = 0; i < _id.length; i++)
                _id[i] = alphabet.charAt(rand.nextInt(alphabet.length()));
        }
        _messages.put(new String(_id), new Message(mes));

    }

    static
    {
        _messages.put("00000000001", new Message("Моё первое сообщение"));
        _messages.put("00000000002", new Message("Здесь будет новое сообщение"));
    }

    @RequestMapping(value = "/messages/{id}", method = RequestMethod.GET)
    @ResponseBody
    String renderMessageById(@RequestParam("id") String _id)
    {
        String message = _messages.get(_id).getMessage();
        return
                "<html>" +
                "   <link rel=\"stylesheet\" type=\"text/css\" href=\"/twitter.css\"/>" +
                "   <body>" +
                "       <h1>twitter</h1>" +
                "       This is your twitter application" +
                "       <ul class=\"messages\">" +
                        message +
                "       </ul>" +
                "   </body>" +
                "</html>";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    @ResponseBody
    String renderAllMessages()
    {
        String messages = _messages
            .values()
            .stream()
            .map(msg -> "<li>" + msg+ "</li>")
            .collect(Collectors.joining());

        return
            "<html>" +
            "   <link rel=\"stylesheet\" type=\"text/css\" href=\"/twitter.css\"/>" +
            "   <body>" +
            "       <h1>twitter</h1>" +
            "       This is your twitter application" +
            "       <ul class=\"messages\">" +
                        messages +
            "       </ul>"+
            "   </body>" +
            "</html>";
    }

    @RequestMapping(value = "/messages/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    void deleteMessage(@RequestParam("id") String _id)
    {
        _messages.remove(_id);
    }

}
