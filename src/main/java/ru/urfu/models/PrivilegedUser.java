package ru.urfu.models;

/**
 * Created by vadim on 14.11.16.
 */
public class PrivilegedUser extends User
{
    public  PrivilegedUser(String login, String password)
    {
        super(login, password);
    }

    @Override
    public void addMessage(String msg)
    {
        messages.add(messages.size()+1, msg);
    }

    /*
    @Override
    public void addFriend(String login)
    {
        friends.add(friends.size()+1, login);
    }
    */
}
