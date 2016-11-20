package ru.urfu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import ru.urfu.storage.UserStorage;

import java.math.BigInteger;

/**
 * Created by vadim on 17.11.16.
 */
@Controller
public class SomeController
{
    @Inject
    private UserStorage userStorage;

    @RequestMapping({"/"})
    public String mainpage()
    {
        return "main";//watch "main.jsp"
    }

    @RequestMapping({"/register"})
    public String register(Model model, HttpMethod method, HttpServletRequest request)
    {
        if (method == HttpMethod.POST)
        {
            String strLog = request.getParameter("login");
            String strPass = request.getParameter("password");
            String strRepPass = request.getParameter("repeatPassword");
            try
            {
                BigInteger id = BigInteger.valueOf(userStorage.size() + 1);
                userStorage.addUser(strLog, strPass, strRepPass);
                model.addAttribute("id",id);
                model.addAttribute("messages",userStorage.getUserMessages(id));
                return "homepage";//watch "homepage.jsp"
            }
            catch (Exception e)
            {
                model.addAttribute("errorMessage", e.getLocalizedMessage());
                return "errorPage";//watch "errorPage.jsp"
            }
        }
        model.addAttribute("errorMessage", "Incorrect http method");
        return "errorPage";//watch "errorPage.jsp"
    }

    @RequestMapping({"/authorize"})
    public String authorize(Model model, HttpMethod method, HttpServletRequest request)
    {
        if (method == HttpMethod.PUT)
        {
            String strLog = request.getParameter("login");
            String strPass = request.getParameter("password");
            if(userStorage.userIsExist(strLog))
            {
                BigInteger id = userStorage.getUserId(strLog, strPass);
                model.addAttribute("id", id);
                model.addAttribute("messages",userStorage.getUserMessages(id));
                return "homepage";//watch "homepage.jsp"
            }
            else
            {
                model.addAttribute("errorMessage", "Incorrect login or password");
                return "errorPage";//watch "errorPage.jsp"
            }
        }
        model.addAttribute("errorMessage", "Incorrect http method");
        return "errorPage";//watch "errorPage.jsp"
    }

    @RequestMapping({"{id}/homepage"})
    public String userHomepage(@PathVariable("id") BigInteger id,
                               Model model, HttpMethod method, HttpServletRequest request)
    {
        if (method == HttpMethod.GET)
        {
            if(userStorage.idIsCorrect(id))
            {
                model.addAttribute("id", id);
                model.addAttribute("messages", userStorage.getUserMessages(id));
                return "homepage";//watch "homepage.jsp"
            }
        }
        model.addAttribute("errorMessage", "Incorrect http method");
        return "errorPage";//watch "errorPage.jsp"
    }

    @RequestMapping({"{id}/add"})
    public String addMessage(@PathVariable("id") BigInteger id,
                             Model model, HttpMethod method, HttpServletRequest request)
    {
        if (method == HttpMethod.POST)
        {
            if(userStorage.idIsCorrect(id))
            {
                String message = request.getParameter("message");
                userStorage.addUserMessage(id, message);
                model.addAttribute("id", id);
                model.addAttribute("messages", userStorage.getUserMessages(id));
                return "add";//watch "add.jsp"
            }
            model.addAttribute("errorMessage", "Incorrect user id");
            return "errorPage";//watch "errorPage.jsp"
        }
        model.addAttribute("errorMessage", "Incorrect http method");
        return "errorPage";//watch "errorPage.jsp"
    }

    @RequestMapping({"{id_user}/delete/{id_message}"})
    public String deleteMessage(@PathVariable("id") BigInteger id,
                             Model model, HttpMethod method, HttpServletRequest request)
    {
        if (method == HttpMethod.DELETE)
        {
            if(userStorage.idIsCorrect(id))
            {
                String message = request.getParameter("message");
                userStorage.addUserMessage(id, message);
                model.addAttribute("id", id);
                model.addAttribute("messages", userStorage.getUserMessages(id));
                return "homepage";//watch "homepage.jsp"
            }
            model.addAttribute("errorMessage", "Incorrect user id");
            return "errorPage";//watch "errorPage.jsp"
        }
        model.addAttribute("errorMessage", "Incorrect http method");
        return "errorPage";//watch "errorPage.jsp"
    }
}
