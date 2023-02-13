package storage;

import model.User;

import java.io.*;
import java.util.List;

public class UserReadWrite extends GetData<User>
{
    private static UserReadWrite userReadWrite;
    private UserReadWrite(String pathDataFile) {
        super(pathDataFile);
    }

    public static UserReadWrite getInstance()
    {
        if (userReadWrite == null)
        {
            userReadWrite = new UserReadWrite("");
        }
        return userReadWrite;
    }

    @Override
    public List<User> readFile()
    {
        try(InputStream userDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(userDataFile) )
        {
            List<User> userList = (List<User>)objectInputStream.readObject();
            return userList;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file !");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Không tìm thấy lớp hợp lệ!");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi đọc file dữ liệu !");
        }
        return null;
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
            System.out.println("Không tìm thấy đường dẫn file !");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi đọc file dữ liệu !");
        }

    }
}
