package ru.urfu;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

/**
 * @author aarkaev
 * @since 08.08.2016
 */
@RestController
public class MessageStorage
{
    private static final Map<String, String> _messages = new HashMap<>();

    private String generateNewKey()
    {
        char _id[] = new char[11];
        String check = _messages.get(new String(_id));
        String alphabet =
                "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        while (check != null)
        {
            for (int i = 0; i < _id.length; i++)
                _id[i] = alphabet.charAt(rand.nextInt(alphabet.length()));
            check = _messages.get(new String(_id));
        }
        return new String(_id);
    }

    @RequestMapping(value = "/messages/add={msg}",method = RequestMethod.POST)
    @ResponseBody
    void addMessage(@PathVariable("msg") String msg)
    {
        _messages.put(generateNewKey(), msg);
    }

    static
    {
        _messages.put("00000000001", "Моё первое сообщение");
        _messages.put("00000000002", "Здесь будет новое сообщение");
    }

    public void printMessageStore()
    {
        System.out.println(_messages);
    }

    List<String> renderMessageIds(String mes)
    {
        return _messages.entrySet()
                .stream()
                .filter(msg -> msg.getValue().equals(mes))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/messages/id={id}", method = RequestMethod.GET)
    @ResponseBody
    String renderMessageById(@PathVariable("id") String id)
    {
        String message = _messages.get(id);
        return
                "<html>" +
                "   <link rel=\"stylesheet\" type=\"text/css\" href=\"/twitter.css\"/>" +
                "   <body>" +
                "       <h1>twitter</h1>" +
                "       This is your twitter application" +
                "       <ul class=\"messages\">" +
                        "<li>" + message + "</li>" +
                "       </ul>" +
                "   </body>" +
                "</html>";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    @ResponseBody
    String renderAllMessages()
    {
        String messages = _messages.values()
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

    @RequestMapping(value = "/messages/id={id}", method = RequestMethod.DELETE)
    @ResponseBody
    void deleteMessage(@PathVariable("id") String id)
    {
        _messages.remove(id);
    }

}
