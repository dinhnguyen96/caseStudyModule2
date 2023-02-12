package controller;

import model.Customer;
import model.User;

import java.io.*;
import java.util.List;

public class UserManager extends ApplicationManager<User>
{

    public UserManager(String pathDataFile) {
        super(pathDataFile);
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
