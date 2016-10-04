package ru.urfu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aarkaev
 * @since 08.08.2016
 */
@RestController
public class MessageStorage {
    private static final List<String> _messages = new ArrayList<>();

    static {
        _messages.add("Моё первое сообщение");
        _messages.add("Здесь будет новое сообщение");
    }

    @RequestMapping("/messages")
    String renderAllMessages() {
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
