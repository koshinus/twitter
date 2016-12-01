package code.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import code.storage.MessageStorage;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by vadim on 27.11.16.
 */
@RestController
public class MessageController
{
    @Inject
    private MessageStorage messageStorage;

    @RequestMapping({"/"})
    public String mainpage()
    {
        return "mainmsg";//watch "mainmsg.jsp"
    }

    @RequestMapping({"/add"})
    public String addMessage(Model model, HttpMethod method, HttpServletRequest request)
    {
        if (method == HttpMethod.POST)
        {
            String message = request.getParameter("message");
            messageStorage.addMessage(message);
            return "addmsg";//watch "addmsg.jsp"
        }
        model.addAttribute("errorMessage", "Incorrect http method");
        return "errorPage";//watch "errorPage.jsp"
    }

    @RequestMapping({"/messages"})
    public String printMessages(Model model, HttpMethod method, HttpServletRequest request)
    {
        if (method == HttpMethod.GET)
        {
            String message = request.getParameter("message");
            messageStorage.addMessage(message);
            model.addAttribute("messages", messageStorage.getMessages());
            return "msgs";//watch "msgs.jsp"
        }
        model.addAttribute("errorMessage", "Incorrect http method");
        return "errorPage";//watch "errorPage.jsp"
    }

    @RequestMapping({"/delete"})
    public String deleteMessage(Model model, HttpMethod method, HttpServletRequest request)
    {
        if (method == HttpMethod.DELETE)
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
            return "delmsg";//watch "delmsg.jsp"
        }
        model.addAttribute("errorMessage", "Incorrect http method");
        return "errorPage";//watch "errorPage.jsp"
    }
}
