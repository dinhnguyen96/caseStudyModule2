package controller;

import model.User;
import storage.UserReadWrite;

import java.util.List;

public class UserManager implements ApplicationManager<User>
{
    private UserReadWrite userReadWrite = UserReadWrite.getInstance();
    public UserManager()
    {

    }

    @Override
    public List<User> readFile()
    {
       return userReadWrite.readFile();
    }

    @Override
    public void writeFile(List<User> userList)
    {
       userReadWrite.writeFile(userList);
    }
}
