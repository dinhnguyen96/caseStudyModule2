package views.admin;

import controller.categories.CategoriesManager;
import controller.manager.GeneralFunction;
import controller.product.ProductManager;
import model.Categories;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminCategories {

    private static GeneralFunction<Categories> categoriesGeneralFunction = new CategoriesManager();


    private static List<Categories> categoriesList = categoriesGeneralFunction.readFile();


    private static void addCategories()
    {
        boolean checked;
        Scanner input = new Scanner(System.in);
        do
        {
            System.out.println("Thêm danh mục ");
            System.out.print("Categories Name :");
            String categoriesName = input.nextLine();
            Categories categories = new Categories(categoriesList.get(categoriesList.size()-1).getId()+1,
                    String.valueOf(categoriesList.get(categoriesList.size()-1).getId()+1),
                    categoriesName);
            checked =  categoriesGeneralFunction.add(categories);

            if (!checked)
            {
                System.out.println("Tên danh mục đã tồn tại");
            }
        }
        while (!checked);
        System.out.println("Thêm thành công ");
        CategoriesManager.categoriesDataCheck = true;
    }

    private static void updateCategories()
    {
        Scanner input = new Scanner(System.in);
        boolean checked = false;
        do {
                System.out.println("Cập nhật danh mục ");
                System.out.print("Categories Code :");
                String categoriesCode = input.nextLine();
                Categories categories = categoriesGeneralFunction.get(categoriesCode);
                if (categories != null)
                {
                    System.out.print("Categories Name :");
                    String categoriesName = input.nextLine();
                    categories.setCategoriesName(categoriesName);
                    categoriesGeneralFunction.update(categories);
                    checked = true;
                }
                else
                {
                    System.out.println("Danh mục này không tồn tại!");
                }
        }
        while (!checked);
        System.out.println("Cập nhật thành công !");
        CategoriesManager.categoriesDataCheck = true;
    }

    private static void removeCategories()
    {
        Scanner input = new Scanner(System.in);
        boolean checked = false;
        do {
            System.out.println("Xóa danh mục ");
            System.out.print("Categories Code :");
            String categoriesCode = input.nextLine();
            Categories categories = categoriesGeneralFunction.get(categoriesCode);
            if (categories != null)
            {
                categoriesGeneralFunction.remove(categories);
                checked = true;
            }
            else
            {
                System.out.println("Danh mục này không tồn tại !");
            }
        }
        while (!checked);
        System.out.println("Xóa thành công ");
        CategoriesManager.categoriesDataCheck = true;

    }

    public static void functionSelection()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("1.Thêm danh mục ");
        System.out.println("2.Cập nhật danh mục ");
        System.out.println("3.Xoá danh mục ");
        System.out.println("4.Tìm kiếm danh mục theo tên ");
        System.out.println("5.Quay lại trang chủ");
        System.out.print("Lựa chọn chức năng danh mục: ");
        int function = Integer.parseInt(input.nextLine());
        switch (function)
        {
            case 1 -> {
               addCategories();
            }
            case 2 -> {
                updateCategories();
            }
            case 3 -> {
               removeCategories();
            }
            case 4 ->
            {
                System.out.print("Nhập danh mục : ");
                String categoriesName = input.nextLine();
                List<Categories> categoriesSeacrh = categoriesGeneralFunction.searchByName(categoriesName);
                if (categoriesSeacrh.size() == 0)
                {
                    System.out.println("Không tìm tháy danh mục");
                }
                else
                {
                    for (Categories categories : categoriesSeacrh)
                    {
                        System.out.println("Categories Id : " + categories.getId());
                        System.out.println("Categories Code : " + categories.getCategoriesCode());
                        System.out.println("Categories Name : " + categories.getCategoriesName());
                    }
                }
            }
            case 5 ->
            {
                AdminTemplate.adminTemplate();
            }
        }
    }
}
