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

    private String generateNewKey()
    {
        char _id[] = new char[11];
        Message check = _messages.get(new String(_id));
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

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    void addMessage(@RequestParam("mes") String mes)
    {
        _messages.put(generateNewKey(), new Message(mes));
    }

    //@RequestMapping(method = RequestMethod.POST)
    //@ResponseBody
    String addMessage(@RequestParam Map.Entry<String, Message> mes) //throws Exception
    {
        String key = mes.getKey();
        Message check = _messages.get(key);
        if(key.length() != 11 || check != null)
        {
            String newKey = generateNewKey();
            _messages.put(newKey, mes.getValue());
            return newKey;
        }
        //throw new Exception("Wrong key length!");
        //if(check != null) throw new Exception("Key is already use!");
        _messages.put(key, mes.getValue());
        return key;
    }


    static
    {
        _messages.put("00000000001", new Message("Моё первое сообщение"));
        _messages.put("00000000002", new Message("Здесь будет новое сообщение"));
    }

    public void printMessageStore()
    {
        System.out.println(_messages);
    }

    @RequestMapping(value = "/messages/{id}", method = RequestMethod.GET)
    @ResponseBody
    String renderMessageById(@RequestParam("id") String id)
    {
        String message = _messages.get(id).getMessage();
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
        String messages = _messages
            .values()
            .stream()
            .map(msg -> "<li>" + msg.getMessage() + "</li>")
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
    void deleteMessage(@RequestParam("id") String id)
    {
        _messages.remove(id);
    }

}
