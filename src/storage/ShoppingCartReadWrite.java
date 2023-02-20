package storage;

import model.CartInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartReadWrite extends ReadWrite<CartInfo>
{
    private static ReadWrite<CartInfo> shoppingCartReadWrite;

    private ShoppingCartReadWrite(String pathDataFile)
    {
        super(pathDataFile);
    }

    public static ReadWrite<CartInfo> getInstance()
    {
        if (shoppingCartReadWrite == null)
        {
            shoppingCartReadWrite = new ShoppingCartReadWrite("src/storage/file/shopping.dat");
        }
        return shoppingCartReadWrite;
    }

    @Override
    public List<CartInfo> readFile()
    {
        try(InputStream cartDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(cartDataFile) )
        {
            List<CartInfo> cartInfoList  = (List<CartInfo>)objectInputStream.readObject();
            return cartInfoList;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file shopping cart !");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Không tìm thấy lớp hợp lệ!");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi đọc dữ liệu file shopping cart");
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
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file shopping cart !");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi ghi file dữ liệu shopping cart !");
        }
    }
}
