package storage;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserReadWrite extends ReadWrite<User>
{
    private static UserReadWrite userReadWrite;
    private UserReadWrite(String pathDataFile) {
        super(pathDataFile);
    }

    public static UserReadWrite getInstance()
    {
        if (userReadWrite == null)
        {
            userReadWrite = new UserReadWrite("src/storage/file/user.dat");
        }
        return userReadWrite;
    }

    @Override
    public List<User> readFile()
    {
        List<User> userList = null;
        try(InputStream userDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(userDataFile) )
        {
           userList = (List<User>)objectInputStream.readObject();

        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file user !");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Không tìm thấy lớp hợp lệ!");
        }
        catch (IOException e)
        {
            userList = new ArrayList<>();
        }
        return userList;
    }

    @Override
    public void writeFile(List<User> userList)
    {
        try(OutputStream userFile = new FileOutputStream(getPathDataFile());
            ObjectOutputStream objectOutStream = new ObjectOutputStream(userFile))
        {
            objectOutStream.writeObject(userList);
            System.out.println("Ghi thành công ! ");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file user !");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi ghi file dữ liệu user !");
        }

    }
}
