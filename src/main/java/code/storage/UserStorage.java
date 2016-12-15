package code.storage;

/**
 * Created by vadim on 11.12.16.
 */
import code.model.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;

import static code.model.User.loginIsVaild;

@Named
public class UserStorage
{
    @Inject
    private static HashMap<BigInteger, User> users;

    public UserStorage()
    {
        users = new HashMap<>();
    }

    public boolean userIsExist(String login)
    {
        for(User user: users.values())
            if(login.equals(user.getLogin()))
                return true;
        return false;
    }

    public int size() { return users.size(); }

    public boolean idIsCorrect(BigInteger id)
    {
        return users.keySet().contains(id);
    }

    public BigInteger getUserId(String login, String password)
    {
        User user = new User(login, password);
        return  getUserId(user);
    }

    public BigInteger getUserId(User user)
    {
        for (BigInteger key : users.keySet())
        {
            if(users.get(key).equals(user))
                return key;
        }
        return null;
    }

    public User get(BigInteger id) { return users.get(id); }

    public User findByLogin(String login)
    {
        for (BigInteger key : users.keySet())
        {
            if(users.get(key).getLogin().equals(login))
                return users.get(key);
        }
        return null;
    }

    public void addUser(String login, String password, String repeatPassword) throws Exception
    {
        if(!loginIsVaild(login))
            throw new Exception("Login is not valid");
        else if(userIsExist(login))
            throw new Exception("User is already exist");
        else if(!password.equals(repeatPassword))
            throw new Exception("Passwords not equal");
        else users.put(BigInteger.valueOf(users.size()), new User(login, password));
    }

    public void addUser(User user) throws Exception
    {
        String login = user.getLogin();
        if(!loginIsVaild(login))
            throw new Exception("Login is not valid");
        else if(userIsExist(login))
            throw new Exception("User is already exist");
        else users.put(BigInteger.valueOf(users.size()), user);
    }

    public void addUserMessage(BigInteger id, String message)
    { users.get(id).addMessage(message); }

    public Collection<String> getUserMessages(BigInteger id)
    { return users.get(id).getMessages(); }
}
