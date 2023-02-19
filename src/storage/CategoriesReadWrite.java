package storage;

import model.Categories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesReadWrite extends ReadWrite<Categories>
{
    private static CategoriesReadWrite categoriesReadWrite;

    private CategoriesReadWrite(String pathDataFile) {
        super(pathDataFile);
    }

    public static CategoriesReadWrite getInstance()
    {
        if (categoriesReadWrite == null)
        {
            categoriesReadWrite = new CategoriesReadWrite("src/storage/file/categories.dat");
        }
        return categoriesReadWrite;
    }

    @Override
    public List<Categories> readFile()
    {
        List<Categories> categoriesList = null;
        try(InputStream categoriesDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(categoriesDataFile) )
        {
            categoriesList = (List<Categories>)objectInputStream.readObject();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file categories !");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Không tìm thấy lớp hợp lệ!");
        }
        catch (IOException e)
        {
           categoriesList = new ArrayList<>();
        }
        return categoriesList;
    }

    @Override
    public void writeFile(List<Categories> categoriesList)
    {
        try(OutputStream categoriesFile = new FileOutputStream(getPathDataFile());
            ObjectOutputStream objectOutStream = new ObjectOutputStream(categoriesFile))
        {
            objectOutStream.writeObject(categoriesList);

        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file categories !");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi ghi file dữ liệu categories !");
        }
    }
}
