package controller;

import model.Customer;
import model.Employee;

import java.io.*;
import java.util.List;

public class EmployeeManager extends ApplicationManager<Employee>
{
    public EmployeeManager(String pathDataFile) {
        super(pathDataFile);
    }

    @Override
    public List<Employee> readFile() {
        try(InputStream employeeDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(employeeDataFile) )
        {
            List<Employee> employeeList = (List<Employee>)objectInputStream.readObject();
            return employeeList;
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
    public void writeFile(List<Employee> employeeList)
    {
        try(OutputStream employeeFile = new FileOutputStream(getPathDataFile());
            ObjectOutputStream objectOutStream = new ObjectOutputStream(employeeFile))
        {
            objectOutStream.writeObject(employeeList);
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
