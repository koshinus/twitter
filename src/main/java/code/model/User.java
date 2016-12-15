package code.model;

//import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vadim on 11.12.16.
 */
public class User
{
    protected String login;
    protected String password;
    protected ArrayList<String> messages;

    public User() {}

    public User(String _login, String _passw)
    {
        login = _login;
        password = _passw;
        messages = new ArrayList<>();
    }

    public void deleteMessage(int id) throws Exception
    {
        if(id < 0 || id >= messages.size()) throw new Exception("Incorrect id");
        messages.remove(id);
    }

    public static boolean loginIsVaild(String _login)
    {
        Pattern p = Pattern.compile("\\w{1,50}");
        Matcher m = p.matcher(_login);
        return m.matches();

    }

    public Collection<String> getMessages()
    {
        return messages;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String new_login)
    {
        login = new_login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String new_passw)
    {
        login = new_passw;
    }

    public void addMessage(String msg)
    {
        if (messages.size() > 1_000_000)
        {
            System.out.println("Too many messages");
            return;
        }
        messages.add(messages.size(), msg);
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof User)) return false;
        User otherUser = (User) other;
        return login.equals(otherUser.login);// &&
        //password.equals(otherUser.password);
    }
}
