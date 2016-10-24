package ru.urfu;

import java.util.Random;

/**
 * Created by vadim on 18.10.16.
 */
public class Message
{
    private String id;
    private String message;

    public Message(String _id, String _message) throws Exception
    {
        if(_id.length() != 11) throw new Exception("Message's ID have wrong size!");
        id = _id;
        message = _message;
    }

    public Message(String _message)
    {
        String alphabet =
                "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        char _id[] = new char[11];
        for(int i = 0; i < 11; i++)
            _id[i] = alphabet.charAt(rand.nextInt(alphabet.length()));
        id = new String(_id);
        message = _message;
    }

    public String getId()
    {
        return id;
    }

    public String getMessage()
    {
        return message;
    }

    public void setId(String _id)
    {
        id = _id;
    }

    public void setMessage(String _message)
    {
        message = _message;
    }

    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Message))return false;
        Message otherMessage = (Message) other;
        return !this.getId().equals(otherMessage.getId()) ||
                !this.getMessage().equals(otherMessage.getMessage());
    }

}
