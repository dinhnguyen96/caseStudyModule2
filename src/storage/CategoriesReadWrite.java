package storage;

import model.Categories;

import java.io.*;
import java.util.List;

public class CategoriesReadWrite extends GetData<Categories>
{
    private static CategoriesReadWrite categoriesReadWrite;

    private CategoriesReadWrite(String pathDataFile) {
        super(pathDataFile);
    }

    public static CategoriesReadWrite getInstance()
    {
        if (categoriesReadWrite == null)
        {
            categoriesReadWrite = new CategoriesReadWrite("");
        }
        return categoriesReadWrite;
    }

    @Override
    public List<Categories> readFile()
    {
        try(InputStream categoriesDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(categoriesDataFile) )
        {
            List<Categories> categoriesList = (List<Categories>)objectInputStream.readObject();
            return categoriesList;
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
    public void writeFile(List<Categories> categoriesList)
    {
        try(OutputStream categoriesFile = new FileOutputStream(getPathDataFile());
            ObjectOutputStream objectOutStream = new ObjectOutputStream(categoriesFile))
        {
            objectOutStream.writeObject(categoriesList);
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
