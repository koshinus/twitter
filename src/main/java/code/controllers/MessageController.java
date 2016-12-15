/*
package code.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import code.storage.MessageStorage;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.AbstractMap;

*/
/**
 * Created by vadim on 27.11.16.
 *//*

@Controller
public class MessageController
{
    @Inject
    private MessageStorage messageStorage;

    @GetMapping(value = "/")
    public String mainpage()
    {
        return "main";//watch "main.jsp"
    }

    @GetMapping(value = "/add")
    public String addMessageGet()
    {
        return "add";//watch "add.jsp"
    }

    @PostMapping(value = "/add")
    public String addMessagePost(Model model, HttpServletRequest request)
    {
        String message = request.getParameter("message");
        messageStorage.addMessage(message);
        model.addAttribute("messages", messageStorage.getMessages());
        return "messages";//watch "add.jsp"
    }

    @GetMapping(value = "/messages")
    public String printMessages(Model model)
    {
        model.addAttribute("messages", messageStorage.getMessages());
        return "messages";//watch "messages.jsp"
    }

    @GetMapping(value = "/delete")
    public String delMessageGet()
    {
        return "delete";//watch "delete.jsp"
    }

    @DeleteMapping(value = "/delete")
    public String delMessageDelete(Model model, HttpServletRequest request)
    {
        int messageId = Integer.parseInt(request.getParameter("id"));
        try
        {
            messageStorage.deleteMessage(messageId);
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "errorPage";//watch "errorPage.jsp"
        }
        //for(AbstractMap.SimpleImmutableEntry<Integer, String> msg : messageStorage.getMessages())
        //    System.out.println(msg.getValue());
        model.addAttribute("messages", messageStorage.getMessages());
        return "messages";//watch "messages.jsp"
    }
}
*/
