package views.admin;

import controller.categories.CategoriesManager;
import controller.employee.EmployeeManager;
import controller.manager.GeneralFunction;
import controller.product.ProductManager;
import controller.user.UserManager;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminEmployee {


    private static GeneralFunction<Employee> employeeGeneralFunction = new EmployeeManager();

    private  static GeneralFunction<User> userGeneralFunction = new UserManager();

    private static List<User> userList = userGeneralFunction.readFile();

    private static List<Employee> employeeList = employeeGeneralFunction.readFile();


    private static void addEmployee()
    {
        Scanner input = new Scanner(System.in);
        List<Roles> roleList = new ArrayList<>();
        roleList.add(new Roles(2, "02","EMPLOYEE"));
        roleList.add(new Roles(3, "03","CUSTOMER"));

        boolean checked = false;
        do {
                System.out.println("Thêm nhân viên ");
                System.out.print("Username : ");
                String username = input.nextLine();
                System.out.print("Password : ");
                String password = input.nextLine();
                System.out.print("Employee Name :");
                String employeeName = input.nextLine();
                System.out.print("Date Of Birth :");
                String dateOfBirth = input.nextLine();
                System.out.print("Place Of Birth : ");
                String placeOfBirth = input.nextLine();
                System.out.print("Email : ");
                String email = input.nextLine();
                User user = new User(userList.get(userList.size()-1).getId()+1,
                                    String.valueOf(userList.get(userList.size()-1).getId()+1),username,password,
                                   roleList);
                Employee employee = new Employee(employeeList.get(employeeList.size()-1).getId()+1,
                             String.valueOf(employeeList.get(employeeList.size()-1).getId()+1),employeeName,
                             dateOfBirth,placeOfBirth, email,user);

                boolean userCheck = userGeneralFunction.add(user);

                if (userCheck)
                {
                    boolean employeeCheck = employeeGeneralFunction.add(employee);
                    if (employeeCheck)
                    {
                        checked = true;
                    }
                }
        }
        while (!checked);
        System.out.println("Thêm thành công ");
        EmployeeManager.employeeDataCheck = true;
        UserManager.userDataCheck = true;
    }

    private static void updateEmployee()
    {
        Scanner input = new Scanner(System.in);
        boolean updateChecked = false;
        do {
                String employeeName="",  employeeDateOfBirth="",  employeePlaceOfBirth="";
                System.out.println("Cập nhật nhân viên ");
                System.out.print("Employee Code :");
                String employeeCode = input.nextLine();
                Employee employee = employeeGeneralFunction.get(employeeCode);
                if (employee != null)
                {
                    System.out.println("Menu");
                    System.out.println("1.Employee Name");
                    System.out.println("2.Employee Date Of Birth");
                    System.out.println("3.Enployee Place Of Birth");
                    System.out.print("Bạn muốn cập nhật thông tin nào : ");
                    int number = Integer.parseInt(input.nextLine());
                    switch (number)
                    {
                        case 1->{
                            System.out.print("Employee Name :");
                            employeeName  = input.nextLine();
                            employee.setEmployeeName(employeeName);
                        }
                        case 2->{
                            System.out.print("Employee Date Of Birth :");
                            employeeDateOfBirth = input.nextLine();
                            employee.setDateOfBirth(employeeDateOfBirth);
                        }
                        case 3->{
                            System.out.print("Employee Place Of Birth : ");
                            employeePlaceOfBirth = input.nextLine();
                            employee.setPlaceOfBirth(employeePlaceOfBirth);
                        }
                    }
                    updateChecked = employeeGeneralFunction.update(employee);
                }
                else
                {
                    System.out.println("Nhân viên này không tồn tại!");
                }
        }
        while (!updateChecked);
        System.out.println("Cập nhật thành công !");
        EmployeeManager.employeeDataCheck = true;
    }

    private static void removeEmployee()
    {
        Scanner input = new Scanner(System.in);
        boolean removeChecked = false;
        do {
            System.out.println("Xóa nhân viên ");
            System.out.print("Employee Code :");
            String employeeCode = input.nextLine();
            Employee employee = employeeGeneralFunction.get(employeeCode);
            if (employee != null)
            {
                User user = userGeneralFunction.get(employee.getUser().getUsername());
                employeeGeneralFunction.remove(employee);
                userGeneralFunction.remove(user);
                removeChecked = true;
            }
        }
        while (!removeChecked);
        System.out.println("Xóa thành công ");
        EmployeeManager.employeeDataCheck = true;
        UserManager.userDataCheck = true;
    }
    public static void functionSelection()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("1.Thêm nhân viên ");
        System.out.println("2.Cập nhật nhân viên  ");
        System.out.println("3.Xoá nhân viên  ");
        System.out.println("4.Tìm kiếm nhân viên theo tên ");
        System.out.println("5.Quay lại trang chủ");
        System.out.print("Lựa chọn chức năng sản phẩm: ");
        int function = Integer.parseInt(input.nextLine());
        switch (function)
        {
            case 1 -> {
               addEmployee();
            }
            case 2 -> {
               updateEmployee();
            }
            case 3 -> {
                removeEmployee();
            }
            case 4 ->{
                System.out.print("Nhập tên nhân viên tìm kiếm : ");
                String employeeName = input.nextLine();
                List<Employee> employeeSearch = employeeSearchbyName(employeeName);
                if (employeeSearch.size() == 0)
                {
                    System.out.println("Không tìm thấy nhân viên");
                }
                else
                {
                    for (Employee employee : employeeSearch)
                    {
                        System.out.println("Employee Code : " + employee.getEmployeeCode());
                        System.out.println("Emploee Name : " + employee.getEmployeeName());
                        System.out.println("Employee Date Of Birth : " + employee.getDateOfBirth());
                        System.out.println("Employee Place Of Birth : " + employee.getPlaceOfBirth());
                    }
                }
            }
            case 5->
            {
                AdminTemplate.adminTemplate();
            }
        }
    }
    private static List<Employee> employeeSearchbyName(String employeeName)
    {;
        List<Employee> searchList = new ArrayList<>();
        String regex = ".*"+employeeName+".*";

        for (Employee employee : employeeList)
        {
            if (employee.getEmployeeName().matches(regex))
            {
               searchList.add(employee);
            }
        }
        return searchList;
    }
}
