package controller;

import model.Customer;
import model.Employee;
import model.User;
import storage.GetData;
import storage.UserReadWrite;

import java.util.List;

public class UserManager implements ApplicationManager<User>, AdditionalFunction<User>
{
    private GetData<User> userReadWrite;

    public UserManager()
    {
       userReadWrite = UserReadWrite.getInstance();
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

    @Override
    public User get(String code)
    {
        for (User user:readFile())
        {
            if (user.getUserCode().equalsIgnoreCase(code))
            {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean add(User user)
    {
        User u = get(user.getUserCode());
        if (u != null)
        {
            return false;
        }
        u = user;
        List<User> updateUserList = readFile();
        updateUserList.add(u);
        writeFile(updateUserList);
        return true;
    }

    @Override
    public boolean update(User user)
    {
        User u = get(user.getUserCode());
        if (u != null)
        {
            List<User> updateUserList = readFile();
            updateUserList.set(updateUserList.indexOf(u), u);
            writeFile(updateUserList);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(User user)
    {
        User u = get(user.getUserCode());
        if (u != null)
        {
            List<User> updateUserList = readFile();
            updateUserList.remove(u);
            writeFile(updateUserList);
            return true;
        }
        return false;
    }
}
