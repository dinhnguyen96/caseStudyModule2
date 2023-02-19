package controller.user;

import controller.manager.GeneralFunction;
import model.User;
import storage.ReadWrite;
import storage.UserReadWrite;

import java.util.List;

public class UserManager implements GeneralFunction<User>
{
    private ReadWrite<User> userReadWrite;

    private  List<User> userList;

    public static boolean userDataCheck = false;

    public UserManager()
    {
       userReadWrite = UserReadWrite.getInstance();
       userList = userReadWrite.readFile();
    }

    @Override
    public List<User> readFile()
    {
        if (userDataCheck)
        {
            userList = userReadWrite.readFile();
            userDataCheck = false;
        }
        return userList;
    }

    @Override
    public void writeFile(List<User> userList)
    {
       userReadWrite.writeFile(userList);
    }

    @Override
    public User get(String codeOrUsername)
    {
        for (User user:readFile())
        {
            if (user.getUserCode().equalsIgnoreCase(codeOrUsername) ||
                    user.getUsername().equals(codeOrUsername))
            {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean add(User user)
    {
        User code = get(user.getUserCode());
        User username = get(user.getUsername());
        if (code != null || username != null)
        {
            return false;
        }
        List<User> updateUserList = readFile();
        updateUserList.add(user);
        writeFile(updateUserList);
        return true;
    }

    @Override
    public void update(User user)
    {
        List<User> updateUserList = readFile();
        updateUserList.set(updateUserList.indexOf(user), user);
        writeFile(updateUserList);
    }

    @Override
    public void remove(User user)
    {
        List<User> updateUserList = readFile();
        updateUserList.remove(user);
        writeFile(updateUserList);
    }
}
