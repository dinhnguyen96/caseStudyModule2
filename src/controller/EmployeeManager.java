package controller;

import model.Employee;
import storage.EmployeeReadWrite;

import java.util.List;

public class EmployeeManager implements ApplicationManager<Employee>
{
    private EmployeeReadWrite employeeReadWrite = EmployeeReadWrite.getInstance();
    public EmployeeManager()
    {

    }

    @Override
    public List<Employee> readFile()
    {
       return employeeReadWrite.readFile();
    }

    @Override
    public void writeFile(List<Employee> employeeList)
    {
       employeeReadWrite.writeFile(employeeList);
    }

    @Override
    public Employee get(String code) {
        return null;
    }

    @Override
    public boolean add(Employee employee) {
        return false;
    }

    @Override
    public boolean update(Employee employee) {
        return false;
    }

    @Override
    public void remove(Employee employee) {

    }
}
