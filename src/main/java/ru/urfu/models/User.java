package ru.urfu.models;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vadim on 14.11.16.
 */
public class User
{
    protected String login;
    protected String password;
    protected ArrayList<String> messages;
    //protected ArrayList<String> friends;

    public User(String _login, String _passw)
    {
        login = _login;
        password = _passw;
    }

    public void deleteMessage(int id) { messages.remove(id); }

    public static boolean loginIsVaild(String _login)
    {
        Pattern p = Pattern.compile("\\w{1,50}");
        Matcher m = p.matcher(_login);
        return m.matches();
    }

    public Collection<String> getMessages() { return messages; }

    public String getLogin() { return login; }

    public void setLogin(String new_login) { login = new_login; }

    public String getPassword() { return password; }

    public void setPassword(String new_passw) { login = new_passw; }

    public void addMessage(String msg)
    {
        if(messages.size() > 1_000_000)
        {
            System.out.println("Too much messages");
            return;
        }
        messages.add(messages.size()+1, msg);
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof User))return false;
        User otherUser = (User)other;
        return login.equals(otherUser.login);// &&
                //password.equals(otherUser.password);
    }

    /*

    public void addFriend(String login);
    public void deleteFriend(String login)
    {
        friends.remove(login);
    }

    @Override
    public void addFriend(String login)
    {
        if(friends.size() > 100_000)
        {
            System.out.println("Too much friends");
            return;
        }
        friends.add(friends.size()+1, login);
    }
    */
}
