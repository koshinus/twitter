package ru.urfu.storage;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vadim on 25.11.16.
 */
@Named
public class MessageStorage
{
    @Inject
    private static ArrayList<String> messages;

    MessageStorage()
    {
        messages = new ArrayList<>();
    }

    public String getMessage(int id)
    {
        return messages.get(id);
    }

    public void addMessage(String message)
    {
        messages.add(message);
    }

    public void deleteMessage(int id) throws Exception
    {
        if(id < 0 || id >= messages.size()) throw new Exception("Incorrect id");
        messages.remove(id);
    }

    public List<AbstractMap.SimpleImmutableEntry<Integer, String>> getMessages()
    {
        LinkedList<AbstractMap.SimpleImmutableEntry<Integer, String>> messagesList =
                new LinkedList<>();
        for(int i = 0; i < messages.size(); i++)
        {
            AbstractMap.SimpleImmutableEntry<Integer, String> pair =
                    new AbstractMap.SimpleImmutableEntry<>(i, messages.get(i));
            messagesList.add(pair);
        }
        return messagesList;
    }
}
