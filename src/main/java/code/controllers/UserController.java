package code.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import code.model.User;
import code.storage.UserStorage;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

/**
 * Created by vadim on 11.12.16.
 */
@Controller
public class UserController
{

    @Inject
    private UserStorage userStorage;

    @GetMapping(value = "/")
    public String mainpage() {
        return "userMain";//watch "main.jsp"
    }

    @GetMapping(value = "/{id}/add")
    public String addMessageGet(@PathVariable("id") BigInteger id, Model model)
    {
        model.addAttribute("id", id);
        return "userMessageAdd";//watch "add.jsp"
    }

    @PostMapping(value = "/{id}/add")
    public String addMessagePost(@PathVariable("id") BigInteger id, Model model, HttpServletRequest request)
    {
        String message = request.getParameter("message");
        //BigInteger val = BigInteger.valueOf(id);
        userStorage.get(id).addMessage(message);
        model.addAttribute("messages", userStorage.get(id).getMessages());
        model.addAttribute("id", id);
        return "userMessages";//watch "add.jsp"
    }

    @GetMapping(value = "/{id}/messages")
    public String printMessages(@PathVariable("id") BigInteger id, Model model)
    {
        model.addAttribute("messages", userStorage.get(id).getMessages());
        model.addAttribute("id", id);
        return "userMessages";//watch "messages.jsp"
    }

    @GetMapping(value = "/{id}/delete")
    public String delMessageGet(@PathVariable("id") BigInteger id, Model model)
    {
        model.addAttribute("id", id);
        return "userMessageDelete";//watch "delete.jsp"
    }

    @DeleteMapping(value = "/{id}/delete")
    public String delMessageDelete(@PathVariable("id") BigInteger id, Model model, HttpServletRequest request)
    {
        //BigInteger val = BigInteger.valueOf(id);
        int messageId = Integer.parseInt(request.getParameter("id"));
        try
        {
            userStorage.get(id).deleteMessage(messageId);
            model.addAttribute("messages", userStorage.get(id).getMessages());
            model.addAttribute("id", id);
            return "userMessages";//watch "messages.jsp"
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "errorPage";//watch "errorPage.jsp"
        }
    }

    @GetMapping("/login")
    public String log()
    {
        return "userLogin";
    }

    @PostMapping("/login")
    public String logForm(Model model, HttpServletRequest request)
    {
        try
        {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if(!userStorage.userIsExist(login))
                throw new Exception("User is not exist!");
            else
            {
                User user = new User(login, password);
                BigInteger id = userStorage.getUserId(user);
                model.addAttribute("id", id);
                return "userMessages";
            }
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "errorPage";//watch "errorPage.jsp"
        }
    }

    @GetMapping("/registration")
    public String reg()
    {
        return "userRegistration";
    }

    @PostMapping("/registration")
    public String regForm(Model model, HttpServletRequest request)
    {
        try
        {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            User user = new User(login, password);
            userStorage.addUser(user);
            BigInteger id = userStorage.getUserId(user);
            model.addAttribute("id", id);
            return "userLogin";
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "errorPage";//watch "errorPage.jsp"
        }
    }

    /*
    @GetMapping("/registration")
    public String reg(Model model)
    {
        User user = new User();
        model.addAttribute("userForm", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String regForm(@ModelAttribute("userForm") User user, Model model)
    {
        try
        {
            userStorage.addUser(user);
            BigInteger id = userStorage.getUserId(user);
            model.addAttribute("id", id);
            return "login";
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "errorPage";//watch "errorPage.jsp"
        }
    }
    */
}