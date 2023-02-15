package controller.employee;

import controller.manager.GeneralFunction;
import model.Employee;
import storage.EmployeeReadWrite;
import storage.GetData;

import java.util.List;

public class EmployeeManager implements GeneralFunction<Employee>
{
    private GetData<Employee> employeeReadWrite;

    private List<Employee> employeeList;
    public EmployeeManager()
    {
        employeeReadWrite = EmployeeReadWrite.getInstance();
        employeeList = employeeReadWrite.readFile();
    }

    @Override
    public List<Employee> readFile()
    {
       return employeeList;
    }

    @Override
    public void writeFile(List<Employee> employeeList)
    {
       employeeReadWrite.writeFile(employeeList);
    }

    @Override
    public Employee get(String code)
    {
        for (Employee employee:readFile())
        {
            if (employee.getEmployeeCode().equalsIgnoreCase(code))
            {
                return employee;
            }
        }
        return null;
    }

    @Override
    public boolean add(Employee employee)
    {
        Employee e = get(employee.getEmployeeCode());
        if (e != null)
        {
            return false;
        }
        e = employee;
        List<Employee> updateEmployeeList = readFile();
        updateEmployeeList.add(e);
        writeFile(updateEmployeeList);
        return true;
    }

    @Override
    public boolean update(Employee employee)
    {
        Employee e = get(employee.getEmployeeCode());
        if (e != null)
        {
            List<Employee> updateEmployeeList = readFile();
            updateEmployeeList.set(updateEmployeeList.indexOf(e), e);
            writeFile(updateEmployeeList);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Employee employee)
    {
        Employee e = get(employee.getEmployeeCode());
        if (e != null)
        {
            List<Employee> updateEmployeeList = readFile();
            updateEmployeeList.remove(e);
            writeFile(updateEmployeeList);
            return true;
        }
        return false;
    }
}