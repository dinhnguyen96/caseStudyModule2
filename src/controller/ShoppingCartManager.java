package controller;

import model.CartInfo;
import model.Product;

import java.io.*;
import java.util.List;

public class ShoppingCartManager extends ApplicationManager<CartInfo>
{
    public ShoppingCartManager(String pathDataFile)
    {
        super(pathDataFile);
    }

    @Override
    public List<CartInfo> readFile()
    {
        try(InputStream cartDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(cartDataFile) )
        {
            List<CartInfo> cartInfoList = (List<CartInfo>)objectInputStream.readObject();
            return cartInfoList;
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
    public void writeFile(List<CartInfo> cartInfoList)
    {
        try(OutputStream cartFile = new FileOutputStream(getPathDataFile());
            ObjectOutputStream objectOutStream = new ObjectOutputStream(cartFile))
        {
            objectOutStream.writeObject(cartInfoList);
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
