package storage;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductReadWrite extends GetData<Product>
{
    private static ProductReadWrite productReadWrite;


    private ProductReadWrite(String pathDataFile)
    {
        super(pathDataFile);
    }

    public static ProductReadWrite getInstance()
    {
        if (productReadWrite == null)
        {
            productReadWrite = new ProductReadWrite("src/storage/file/product.dat");
        }
        return productReadWrite;
    }

    @Override
    public List<Product> readFile() {

        List<Product> productList = null;
        try(InputStream productDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(productDataFile) )
        {
            productList= (List<Product>)objectInputStream.readObject();
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
            productList = new ArrayList<>();
        }
        return productList;
    }

    @Override
    public void writeFile(List<Product> productList)
    {
        try(OutputStream productFile = new FileOutputStream(getPathDataFile());
            ObjectOutputStream objectOutStream = new ObjectOutputStream(productFile))
        {
            objectOutStream.writeObject(productList);
            System.out.println("Ghi thành công ! ");

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
