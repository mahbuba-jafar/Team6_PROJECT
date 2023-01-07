package service;

import entity.Products;
import enums.ErrorCodeEnum;
import exception.InvalidInputException;
import exception.NotFoundException;
import service.inter.MenuServiceInter;
import util.InputUtil;
import util.MenuUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.List;

public class MenuService implements MenuServiceInter {
    ProductService productService = new ProductService();

    @Override
    public void getSectionOne(Path path) throws IOException {
        try {
            String selectedSection1 = InputUtil.requireText("Select");
            if (selectedSection1.equals("a")) {
                productService.listRandomlySelectedEntities(path);
            } else if (selectedSection1.equals("b")) {
                productService.listTopEntities(path);
            } else if (selectedSection1.equals("c")) {
                productService.listBottomEntities(path);
            } else if (selectedSection1.equals("d")) {
                productService.listFields(path);
            }
        } catch (InputMismatchException e) {
            throw new InvalidInputException(ErrorCodeEnum.INVALID_INPUT);
        } catch (NullPointerException n) {
            throw new NotFoundException(ErrorCodeEnum.NOT_FOUND);
        }
    }

    @Override
    public void getSectionTwo(Path path) throws IOException {
        try {
            String selectedSection1 = InputUtil.requireText("Select");
            if (selectedSection1.equalsIgnoreCase("asc")) {
                List<Products> listAsc = productService.sortASC(path);
                listAsc.forEach(System.out::println);
                MenuUtil.listSize(listAsc);
                System.out.println("1.Sort again\n" + "2.Already sorted entites");
                int sortAgainOrAlreadySorted = InputUtil.requireNum("Select");
                if (sortAgainOrAlreadySorted == 1) {
                    MenuUtil.getSectionTwoText();
                    getSectionTwo(path);
                } else if (sortAgainOrAlreadySorted == 2) {
                    listAsc.forEach(System.out::println);
                }
            } else if (selectedSection1.equalsIgnoreCase("desc")) {
                List<Products> listSortDescProducts = productService.sortDESC(path);
                listSortDescProducts.forEach(System.out::println);
                MenuUtil.listSize(listSortDescProducts);
                System.out.println("1.Sort again\n" + "2.Already sorted entites");
                int sortAgainOrAlreadySorted = InputUtil.requireNum("Select");
                if (sortAgainOrAlreadySorted == 1) {
                    MenuUtil.getSectionTwoText();
                    getSectionTwo(path);
                } else if (sortAgainOrAlreadySorted == 2) {
                    listSortDescProducts.forEach(System.out::println);
                }
            }
        } catch (
                InputMismatchException e) {
            throw new InvalidInputException(ErrorCodeEnum.INVALID_INPUT);
        } catch (NullPointerException n) {
            throw new NotFoundException(ErrorCodeEnum.NOT_FOUND);
        }
    }

    @Override
    public void getSectionThree(Path path) throws IOException {
        try {
            System.out.println(MenuUtil.getFieldNames());
            int selectedField = InputUtil.requireNum("Select");
            if (selectedField != 7) {
                String inputValue = InputUtil.requireText("Enter value");
                List<Products> products = productService.searchTheEntites(path, selectedField, inputValue);
                products.forEach(System.out::println);
            } else {
                List<Products> products = productService.searchTheEntites(path, selectedField, null);
                products.forEach(System.out::println);
            }


        } catch (InputMismatchException e) {
            throw new InvalidInputException(ErrorCodeEnum.INVALID_INPUT);
        } catch (NullPointerException n) {
            throw new NotFoundException(ErrorCodeEnum.NOT_FOUND);
        }
    }

    @Override
    public void getSectionFour(Path path) throws IOException {
        try {
            productService.columnNames(path);
        } catch (InputMismatchException e) {
            throw new InvalidInputException(ErrorCodeEnum.INVALID_INPUT);
        } catch (NullPointerException n) {
            throw new NotFoundException(ErrorCodeEnum.NOT_FOUND);
        }
    }

    @Override
    public void getSectionFive(Path path) throws IOException {
        try {
            productService.filterEntities(path);
        } catch (InputMismatchException e) {
            throw new InvalidInputException(ErrorCodeEnum.INVALID_INPUT);
        } catch (NullPointerException n) {
            throw new NotFoundException(ErrorCodeEnum.NOT_FOUND);
        }
    }
}
