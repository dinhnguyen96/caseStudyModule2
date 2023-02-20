package storage;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductReadWrite extends ReadWrite<Product>
{
    private static ReadWrite<Product> productReadWrite;


    private ProductReadWrite(String pathDataFile)
    {
        super(pathDataFile);
    }

    public static ReadWrite<Product> getInstance()
    {
        if (productReadWrite == null)
        {
            productReadWrite = new ProductReadWrite("src/storage/file/product.dat");
        }
        return productReadWrite;
    }

    @Override
    public List<Product> readFile() {

        try(InputStream productDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(productDataFile) )
        {
            List<Product> productList = (List<Product>)objectInputStream.readObject();
            return productList;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file product !");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Không tìm thấy lớp hợp lệ!");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi đọc dữ liệu file product");
        }
        return null;

    }

    @Override
    public void writeFile(List<Product> productList)
    {
        try(OutputStream productFile = new FileOutputStream(getPathDataFile());
            ObjectOutputStream objectOutStream = new ObjectOutputStream(productFile))
        {
            objectOutStream.writeObject(productList);

        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file product !");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi ghi file dữ liệu product !");
        }
    }

}
