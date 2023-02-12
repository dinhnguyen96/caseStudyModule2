package controller;

import model.Product;

import java.io.*;
import java.util.List;

public class ProductManager extends ApplicationManager<Product>
{
    public ProductManager(String pathDataFile)
    {
        super(pathDataFile);
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
            System.out.println("Không tìm thấy đường dẫn file !");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi đọc file dữ liệu !");
        }
    }
}
