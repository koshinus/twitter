package ru.urfu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

/**
 * @author aarkaev
 * @since 08.08.2016
 */
@RestController
public class MessageStorage
{
    private static final List<Message> _messages = new ArrayList<>();

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    void addMessages(@RequestParam("mes") String mes)
    {
        _messages.add(new Message(mes));
    }
    /*
    static
    {
        _messages.add("Моё первое сообщение");
        _messages.add("Здесь будет новое сообщение");
    }
    */
    @RequestMapping(value = "/messages/{id}", method = RequestMethod.GET)
    @ResponseBody
    String renderMessageById(@RequestParam("id") int _id) throws Exception
    {
        if(_messages.size() < _id || _id < 1)
            throw new Exception("Incorrect message index!");
        else
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
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    @ResponseBody
    String renderAllMessages()
    {
        String messages = _messages
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

}
