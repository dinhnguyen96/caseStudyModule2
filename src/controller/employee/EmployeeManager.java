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
    public Employee get(String employeeCodeEmailUser)
    {
        for (Employee employee:readFile())
        {
            if (employee.getEmployeeCode().equalsIgnoreCase(employeeCodeEmailUser) ||
                    employee.getUser().getUsername().equalsIgnoreCase(employeeCodeEmailUser) ||
                    employee.getEmail().equalsIgnoreCase(employeeCodeEmailUser) )
            {
                return employee;
            }
        }
        return null;
    }

    @Override
    public boolean add(Employee employee)
    {
        Employee employeeCode = get(employee.getEmployeeCode());
        Employee employeeUsername = get(employee.getUser().getUsername());
        Employee employeeEmail = get(employee.getEmail());
        if (employeeCode != null || employeeUsername != null || employeeEmail != null)
        {
            return false;
        }
        List<Employee> updateEmployeeList = readFile();
        updateEmployeeList.add(employee);
        writeFile(updateEmployeeList);
        return true;
    }
    @Override
    public boolean update(Employee employee)
    {
        List<Employee> updateEmployeeList = readFile();
        updateEmployeeList.set(updateEmployeeList.indexOf(employee), employee);
        writeFile(updateEmployeeList);
        return true;
    }

    @Override
    public boolean remove(Employee employee)
    {
        List<Employee> updateEmployeeList = readFile();
        updateEmployeeList.remove(employee);
        writeFile(updateEmployeeList);
        return true;
    }
}
