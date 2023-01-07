package util;

import entity.Products;
import enums.ErrorCodeEnum;
import exception.InvalidInputException;
import service.MenuService;
import service.inter.MenuServiceInter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.List;

public class MenuUtil {
    MenuService menuService = new MenuService();

    public void menuList(Path path) throws IOException {
        getMenuText();
        try {
            int selectedSection = InputUtil.requireNum("Select");
            if (selectedSection == 1) {
                getSectionOneText();
                menuService.getSectionOne(path);
                menuList(path);
            } else if (selectedSection == 2) {
                getSectionTwoText();
                menuService.getSectionTwo(path);
                menuList(path);
            } else if (selectedSection == 3) {
                menuService.getSectionThree(path);
                menuList(path);
            } else if (selectedSection == 4) {
                menuService.getSectionFour(path);
                menuList(path);
            } else if (selectedSection == 5) {
                menuService.getSectionFive(path);
                menuList(path);
            }
        } catch (InputMismatchException e) {
            throw new InvalidInputException(ErrorCodeEnum.INVALID_INPUT);
        }


    }

    public static void getMenuText() {
        String menu = "1. List all the entities \n" +
                "2. Sort the entities\n" +
                "3. Search entities based on any given field and value\n" +
                "4. List column names\n" +
                "5. Filter entities\n";
        System.out.println(menu);
    }


    public static void getSectionOneText() {
        String fSection = "a. List randomly selected 20 entities \n" +
                "b. List top 20 entities\n" +
                "c. List bottom 20 entities\n" +
                "d. List selected fields\n";
        System.out.println(fSection);
    }

    public static void getSectionTwoText() {
        String tSection = "(ASC,DESC)";
        System.out.println(tSection);
    }

    public static String getIntOrFloatFieldNames() {
        String fieldNames = "1.id\n" +
                "2.item_id\n" +
                "3.price\n" +
                "4.old_price\n" +
                "5.depth\n" +
                "6.height\n" +
                "7.width\n";
        return fieldNames;
    }

    public static String getFieldNames() {
        String fieldNames = "1.id\n" +
                "2.item_id\n" +
                "3.name\n" +
                "4.category\n" +
                "5.price\n" +
                "6.old_price\n" +
                "7.sellable_online\n" +
                "8.link\n" +
                "9.other_colors\n" +
                "10.short_description\n" +
                "11.designer\n" +
                "12.depth\n" +
                "13.height\n" +
                "14.width\n";
        return fieldNames;
    }

    public static void listSize(List list) {
        System.out.println("Number of entites listed:" + list.size());
    }

    public static void inputSelectedField(Products products, int inputFieldName) {
        if (inputFieldName == 1) {
            System.out.println(products.getId());
        } else if (inputFieldName == 2) {
            System.out.println(products.getItem_id());
        } else if (inputFieldName == 3) {
            System.out.println(products.getName());
        } else if (inputFieldName == 4) {
            System.out.println(products.getCategory());
        } else if (inputFieldName == 5) {
            System.out.println(products.getPrice());
        } else if (inputFieldName == 6) {
            System.out.println(products.getOld_price());
        } else if (inputFieldName == 7) {
            System.out.println(products.isSellable_online());
        } else if (inputFieldName == 8) {
            System.out.println(products.getLink());
        } else if (inputFieldName == 9) {
            System.out.println(products.getOther_colors());
        } else if (inputFieldName == 10) {
            System.out.println(products.getShort_description());
        } else if (inputFieldName == 11) {
            System.out.println(products.getDesigner());
        } else if (inputFieldName == 12) {
            System.out.println(products.getDepth());
        } else if (inputFieldName == 13) {
            System.out.println(products.getHeight());
        } else if (inputFieldName == 14) {
            System.out.println(products.getWidth());
        }
    }

    public static void filterMethodText(){
        String filterMethod = "1. equal \n" +
                "2. greater than \n" +
                "3. less than \n" +
                "4. greater and equal to \n" +
                "5. less and equal to \n" +
                "6. between ";
        System.out.println(filterMethod);
    }
}
