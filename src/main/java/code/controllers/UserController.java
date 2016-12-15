package code.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return "userMain";//watch "userMain.jsp"
    }

    @GetMapping(value = "/add")
    public String addMessageGet()
    {
        return "userMessageAdd";//watch "userMessageAdd.jsp"
    }

    @PostMapping(value = "/add")
    public String addMessagePost(Model model, HttpServletRequest request)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BigInteger id = userStorage.getIdByLogin((String)authentication.getPrincipal());
        String message = request.getParameter("message");
        userStorage.addUserMessage(id, message);
        model.addAttribute("messages", userStorage.getUserMessages(id));
        return "userMessages";//watch "userMessages.jsp"
    }

    @GetMapping(value = "/messages")
    public String printMessages(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BigInteger id = userStorage.getIdByLogin((String)authentication.getPrincipal());
        model.addAttribute("messages", userStorage.getUserMessages(id));
        return "userMessages";//watch "userMessages.jsp"
    }

    @GetMapping(value = "/delete")
    public String delMessageGet()
    {
        return "userMessageDelete";//watch "userMessageDelete.jsp"
    }

    @PostMapping(value = "/delete")
    public String delMessageDelete(Model model, HttpServletRequest request)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BigInteger id = userStorage.getIdByLogin((String)authentication.getPrincipal());
        int messageId = Integer.parseInt(request.getParameter("id"));
        try
        {
            userStorage.deleteUserMessage(id, messageId);
            model.addAttribute("messages", userStorage.getUserMessages(id));
            return "userMessages";//watch "userMessages.jsp"
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "errorPage";//watch "errorPage.jsp"
        }
    }

    @GetMapping("/login")
    public String login()
    {
        return "userLogin";
    }

    @GetMapping("/registration")
    public String reg(Model model)
    {
        User user = new User();
        model.addAttribute("userForm", user);
        return "userRegistration";
    }

    @PostMapping("/registration")
    public String regForm(@ModelAttribute("userForm") User user, Model model)
    {
        try
        {
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

}