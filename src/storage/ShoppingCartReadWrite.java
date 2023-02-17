package storage;

import model.CartInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartReadWrite extends GetData<CartInfo>
{
    private static ShoppingCartReadWrite shoppingCartReadWrite;

    private ShoppingCartReadWrite(String pathDataFile)
    {
        super(pathDataFile);
    }

    public static ShoppingCartReadWrite getInstance()
    {
        if (shoppingCartReadWrite == null)
        {
            shoppingCartReadWrite = new ShoppingCartReadWrite("src/storage/file/shoppingcart.dat");
        }
        return shoppingCartReadWrite;
    }

    @Override
    public List<CartInfo> readFile()
    {
        List<CartInfo> cartInfoList = null;
        try(InputStream cartDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(cartDataFile) )
        {
            cartInfoList = (List<CartInfo>)objectInputStream.readObject();
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
           cartInfoList = new ArrayList<>();
        }
        return cartInfoList;
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
