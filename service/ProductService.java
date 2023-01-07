package service;

import entity.Products;
import enums.ErrorCodeEnum;
import exception.InvalidInputException;
import service.inter.ProductServiceInter;
import util.CSVFileStreamUtil;
import util.InputUtil;
import util.MenuUtil;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class ProductService implements ProductServiceInter {
    public static List<Products> list = new ArrayList<>();

    @Override
    public void listRandomlySelectedEntities(Path path) throws IOException {
        List<Products> resultList = new ArrayList<>();
        Random r = new Random();
        list = Files.lines(path)
                .skip(1)
                .map(CSVFileStreamUtil::getProducts)
                .collect(Collectors.toList());
        for (int i = 0; i < 20; i++) {
            resultList.add(list.get(r.nextInt(20)));
        }
        resultList.forEach(System.out::println);
        MenuUtil.listSize(list);
    }

    @Override
    public void listTopEntities(Path path) throws IOException {
        list = Files.lines(path)
                .skip(1)
                .map(CSVFileStreamUtil::getProducts)
                .sorted(comparing(Products::getId, comparing(Math::abs)))
                .limit(20)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        MenuUtil.listSize(list);
    }

    @Override
    public void listBottomEntities(Path path) throws IOException {
        list = Files.lines(path)
                .skip(1)
                .map(CSVFileStreamUtil::getProducts)
                .sorted(comparing(Products::getId, comparing(Math::abs)).reversed())
                .limit(20)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        MenuUtil.listSize(list);
    }

    @Override
    public void listFields(Path path) throws IOException {
        try {
            System.out.println("1. all the fields of each entity.\n" +
                    "2. only the selected fields of each entity.\n");
            int allTheField = InputUtil.requireNum("Select");

            if (allTheField == 1) {
                Files.lines(path)
                        .skip(1)
                        .map(CSVFileStreamUtil::getProducts)
                        .collect(Collectors.toList())
                        .forEach(System.out::println);
            } else if (allTheField == 2) {
                String fieldNames = MenuUtil.getFieldNames();
                System.out.println(fieldNames);

                int inputFieldName = InputUtil.requireNum("Select");

                list = Files.lines(path)
                        .skip(1)
                        .map(CSVFileStreamUtil::getProducts)
                        .collect(Collectors.toList());

                for (Products products : list) {
                    MenuUtil.inputSelectedField(products, inputFieldName);
                }
            }
        } catch (InputMismatchException e) {
            throw new InvalidInputException(ErrorCodeEnum.INVALID_INPUT);
        }
    }

    @Override

    public List<Products> sortASC(Path path) throws IOException {
        System.out.println(MenuUtil.getIntOrFloatFieldNames());
        int selectedField = InputUtil.requireNum("Select");
        if (selectedField == 1) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getId))
                    .collect(Collectors.toList());
            list.forEach(System.out::println);
        } else if (selectedField == 2) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getItem_id))
                    .collect(Collectors.toList());
        } else if (selectedField == 3) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getPrice))
                    .collect(Collectors.toList());
        } else if (selectedField == 4) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getOld_price))
                    .collect(Collectors.toList());
        } else if (selectedField == 5) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getDepth))
                    .collect(Collectors.toList());
        } else if (selectedField == 6) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getHeight))
                    .collect(Collectors.toList());
        } else if (selectedField == 7) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getWidth))
                    .collect(Collectors.toList());
        }
        return list;
    }

    @Override

    public List<Products> sortDESC(Path path) throws IOException {
        System.out.println(MenuUtil.getIntOrFloatFieldNames());
        int selectedField = InputUtil.requireNum("Select");
        if (selectedField == 1) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getId).reversed())
                    .collect(Collectors.toList());
        } else if (selectedField == 2) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getItem_id).reversed())
                    .collect(Collectors.toList());
        } else if (selectedField == 3) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getPrice).reversed())
                    .collect(Collectors.toList());
        } else if (selectedField == 4) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getOld_price).reversed())
                    .collect(Collectors.toList());
        } else if (selectedField == 5) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getDepth).reversed())
                    .collect(Collectors.toList());
        } else if (selectedField == 6) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getHeight).reversed())
                    .collect(Collectors.toList());
        } else if (selectedField == 7) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .sorted(comparing(Products::getWidth).reversed())
                    .collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public List<Products> searchTheEntites(Path path, int selectedField, String inputValue) throws IOException {
        if (selectedField == 1) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.getId().equals(Integer.parseInt(inputValue)))
                    .collect(Collectors.toList());
        } else if (selectedField == 2) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.getItem_id().equals(Integer.parseInt(inputValue)))
                    .collect(Collectors.toList());
        } else if (selectedField == 3) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.getName().toLowerCase().contains(inputValue.toLowerCase()))
                    .collect(Collectors.toList());
        } else if (selectedField == 4) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.getCategory().toLowerCase().contains(inputValue.toLowerCase()))
                    .collect(Collectors.toList());
        } else if (selectedField == 5) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.getPrice().equals(Float.parseFloat(inputValue)))
                    .collect(Collectors.toList());
        } else if (selectedField == 6) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.getOld_price().equals(Float.parseFloat(inputValue)))
                    .collect(Collectors.toList());
        } else if (selectedField == 7) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.isSellable_online())
                    .collect(Collectors.toList());
        } else if (selectedField == 8) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.getLink().toLowerCase().contains(inputValue.toLowerCase()))
                    .collect(Collectors.toList());
        } else if (selectedField == 9) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.getOther_colors().toLowerCase().contains(inputValue.toLowerCase()))
                    .collect(Collectors.toList());
        } else if (selectedField == 10) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.getShort_description().toLowerCase().contains(inputValue.toLowerCase()))
                    .collect(Collectors.toList());
        } else if (selectedField == 11) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.getDesigner().toLowerCase().contains(inputValue.toLowerCase()))
                    .collect(Collectors.toList());
        } else if (selectedField == 12) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.getDepth().equals(Float.parseFloat(inputValue)))
                    .collect(Collectors.toList());
        } else if (selectedField == 13) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.getHeight().equals(Float.parseFloat(inputValue)))
                    .collect(Collectors.toList());
        } else if (selectedField == 14) {
            list = Files.lines(path)
                    .skip(1)
                    .map(CSVFileStreamUtil::getProducts)
                    .filter(products -> products.getWidth().equals(Float.parseFloat(inputValue)))
                    .collect(Collectors.toList());
        } else {
            System.out.println("You must choose a number between [1-14]");
        }
        return list;
    }

    @Override
    public void columnNames(Path path) {
        String file = "src/resources/ikea.csv";
        String line;
        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {
            line = br.readLine();
            System.out.println(line + "\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Products> filterEntities(Path path) throws IOException {
        List<Products> list = Files.lines(path)
                .skip(1)
                .map(CSVFileStreamUtil::getProducts)
                .collect(Collectors.toList());
        while (true) {
            System.out.println(MenuUtil.getFieldNames());
            int selectedField = InputUtil.requireNum("Select");
            if (selectedField == 1) {
                list = filterId(path).stream().collect(Collectors.toList());
                list.forEach(System.out::println);
            } else if (selectedField == 2) {
                list = filterItemId(path).stream().collect(Collectors.toList());
                list.forEach(System.out::println);
            } else if (selectedField == 3) {
                String inputValue = InputUtil.requireText("Enter value");
                list = list.stream()
                        .filter(products -> products.getName().toLowerCase().contains(inputValue.toLowerCase()))
                        .collect(Collectors.toList());
                list.forEach(System.out::println);
            } else if (selectedField == 4) {
                String inputValue = InputUtil.requireText("Enter value");
                list = list.stream()
                        .filter(products -> products.getCategory().toLowerCase().contains(inputValue.toLowerCase()))
                        .collect(Collectors.toList());
                list.forEach(System.out::println);
            } else if (selectedField == 5) {
                list = filterPrice(path).stream().collect(Collectors.toList());
                list.forEach(System.out::println);
            } else if (selectedField == 6) {
                list = filterOldPrice(path).stream().collect(Collectors.toList());
                list.forEach(System.out::println);
            } else if (selectedField == 7) {
                list = list.stream()
                        .filter(Products::isSellable_online)
                        .collect(Collectors.toList());
                list.forEach(System.out::println);
            } else if (selectedField == 8) {
                String inputValue = InputUtil.requireText("Enter value");
                list = list.stream()
                        .filter(products -> products.getLink().toLowerCase().contains(inputValue.toLowerCase()))
                        .collect(Collectors.toList());
                list.forEach(System.out::println);
            } else if (selectedField == 9) {
                String inputValue = InputUtil.requireText("Enter value");
                list = list.stream()
                        .filter(products -> products.getOther_colors().toLowerCase().contains(inputValue.toLowerCase()))
                        .collect(Collectors.toList());
                list.forEach(System.out::println);
            } else if (selectedField == 10) {
                String inputValue = InputUtil.requireText("Enter value");
                list = list.stream()
                        .filter(products -> products.getShort_description().toLowerCase().contains(inputValue.toLowerCase()))
                        .collect(Collectors.toList());
                list.forEach(System.out::println);
            } else if (selectedField == 11) {
                String inputValue = InputUtil.requireText("Enter value");
                list = list.stream()
                        .filter(products -> products.getDesigner().toLowerCase().contains(inputValue.toLowerCase()))
                        .collect(Collectors.toList());
                list.forEach(System.out::println);
            } else if (selectedField == 12) {
                list = filterDepth(path).stream().collect(Collectors.toList());
                list.forEach(System.out::println);
            } else if (selectedField == 13) {
                list = filterHeight(path).stream().collect(Collectors.toList());
                list.forEach(System.out::println);
            } else if (selectedField == 14) {
                list = filterWidth(path).stream().collect(Collectors.toList());
                list.forEach(System.out::println);
            } else {
                System.out.println("You must choose a number between [1-14]");
            }
            System.out.println("1.Exit\n" +
                    "2.Continue");
            int exCont = InputUtil.requireNum("Select");
            if (exCont == 1) {
                break;
            }
        }
        return list;
    }

    @Override
    public List<Products> filterId(Path path) throws IOException {
        MenuUtil.filterMethodText();

        int inputFilterMethod = InputUtil.requireNum("Select");

        List<Products> list = Files.lines(path)
                .skip(1)
                .map(CSVFileStreamUtil::getProducts)
                .collect(Collectors.toList());

        String inputValue = InputUtil.requireText("Enter value");

        if (inputFilterMethod == 1) {
            list = list.stream()
                    .filter(products -> products.getId().equals(Integer.parseInt(inputValue)))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 2) {
            list = list.stream()
                    .filter(products -> products.getId() > Integer.parseInt(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 3) {
            list = list.stream()
                    .filter(products -> products.getId() < Integer.parseInt(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 4) {
            list = list.stream()
                    .filter(products -> products.getId() >= Integer.parseInt(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 5) {
            list = list.stream()
                    .filter(products -> products.getId() <= Integer.parseInt(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 6) {
            int inputBetween = InputUtil.requireNum("Enter second value");
            list = list.stream()
                    .filter(products -> products.getId() > Integer.parseInt(inputValue))
                    .filter(products -> products.getId() < inputBetween)
                    .collect(Collectors.toList());
        }

        return list;
    }

    @Override
    public List<Products> filterItemId(Path path) throws IOException {
        MenuUtil.filterMethodText();

        int inputFilterMethod = InputUtil.requireNum("Select");
        String inputValue = InputUtil.requireText("Enter value");

        List<Products> list = Files.lines(path)
                .skip(1)
                .map(CSVFileStreamUtil::getProducts)
                .collect(Collectors.toList());

        if (inputFilterMethod == 1) {
            list = list.stream()
                    .filter(products -> products.getItem_id().equals(Integer.parseInt(inputValue)))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 2) {
            list = list.stream()
                    .filter(products -> products.getItem_id() > Integer.parseInt(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 3) {
            list = list.stream()
                    .filter(products -> products.getItem_id() < Integer.parseInt(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 4) {
            list = list.stream()
                    .filter(products -> products.getItem_id() >= Integer.parseInt(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 5) {
            list = list.stream()
                    .filter(products -> products.getItem_id() <= Integer.parseInt(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 6) {
            int inputBetween = InputUtil.requireNum("Enter second value");
            list = list.stream()
                    .filter(products -> products.getItem_id() > Integer.parseInt(inputValue))
                    .filter(products -> products.getItem_id() < inputBetween)
                    .collect(Collectors.toList());
        }

        return list;
    }

    @Override
    public List<Products> filterPrice(Path path) throws IOException {
        MenuUtil.filterMethodText();
        int inputFilterMethod = InputUtil.requireNum("Select");

        List<Products> list = Files.lines(path)
                .skip(1)
                .map(CSVFileStreamUtil::getProducts)
                .collect(Collectors.toList());
        String inputValue = InputUtil.requireText("Enter value");

        if (inputFilterMethod == 1) {
            list = list.stream()
                    .filter(products -> products.getPrice().equals(Float.parseFloat(inputValue)))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 2) {
            list = list.stream()
                    .filter(products -> products.getPrice() > Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 3) {
            list = list.stream()
                    .filter(products -> products.getPrice() < Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 4) {
            list = list.stream()
                    .filter(products -> products.getPrice() >= Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 5) {
            list = list.stream()
                    .filter(products -> products.getPrice() <= Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 6) {
            int inputBetween = InputUtil.requireNum("Enter second value");
            list = list.stream()
                    .filter(products -> products.getPrice() > Float.parseFloat(inputValue))
                    .filter(products -> products.getPrice() < inputBetween)
                    .collect(Collectors.toList());
        }

        return list;
    }

    @Override
    public List<Products> filterOldPrice(Path path) throws IOException {
        MenuUtil.filterMethodText();
        int inputFilterMethod = InputUtil.requireNum("Select");

        List<Products> list = Files.lines(path)
                .skip(1)
                .map(CSVFileStreamUtil::getProducts)
                .collect(Collectors.toList());
        String inputValue = InputUtil.requireText("Enter value");

        if (inputFilterMethod == 1) {
            list = list.stream()
                    .filter(products -> products.getOld_price().equals(Float.parseFloat(inputValue)))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 2) {
            list = list.stream()
                    .filter(products -> products.getOld_price() > Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 3) {
            list = list.stream()
                    .filter(products -> products.getOld_price() < Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 4) {
            list = list.stream()
                    .filter(products -> products.getOld_price() >= Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 5) {
            list = list.stream()
                    .filter(products -> products.getOld_price() <= Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 6) {
            int inputBetween = InputUtil.requireNum("Enter second value");
            list = list.stream()
                    .filter(products -> products.getOld_price() > Float.parseFloat(inputValue))
                    .filter(products -> products.getOld_price() < inputBetween)
                    .collect(Collectors.toList());
        }

        return list;
    }

    @Override
    public List<Products> filterDepth(Path path) throws IOException {
        MenuUtil.filterMethodText();
        int inputFilterMethod = InputUtil.requireNum("Select");

        List<Products> list = Files.lines(path)
                .skip(1)
                .map(CSVFileStreamUtil::getProducts)
                .collect(Collectors.toList());
        String inputValue = InputUtil.requireText("Enter value");

        if (inputFilterMethod == 1) {
            list = list.stream()
                    .filter(products -> products.getDepth().equals(Float.parseFloat(inputValue)))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 2) {
            list = list.stream()
                    .filter(products -> products.getDepth() > Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 3) {
            list = list.stream()
                    .filter(products -> products.getDepth() < Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 4) {
            list = list.stream()
                    .filter(products -> products.getDepth() >= Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 5) {
            list = list.stream()
                    .filter(products -> products.getDepth() <= Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 6) {
            int inputBetween = InputUtil.requireNum("Enter second value");
            list = list.stream()
                    .filter(products -> products.getDepth() > Float.parseFloat(inputValue))
                    .filter(products -> products.getDepth() < inputBetween)
                    .collect(Collectors.toList());
        }

        return list;
    }

    @Override
    public List<Products> filterHeight(Path path) throws IOException {
        MenuUtil.filterMethodText();
        int inputFilterMethod = InputUtil.requireNum("Select");

        List<Products> list = Files.lines(path)
                .skip(1)
                .map(CSVFileStreamUtil::getProducts)
                .collect(Collectors.toList());
        String inputValue = InputUtil.requireText("Enter value");

        if (inputFilterMethod == 1) {
            list = list.stream()
                    .filter(products -> products.getHeight().equals(Float.parseFloat(inputValue)))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 2) {
            list = list.stream()
                    .filter(products -> products.getHeight() > Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 3) {
            list = list.stream()
                    .filter(products -> products.getHeight() < Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 4) {
            list = list.stream()
                    .filter(products -> products.getHeight() >= Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 5) {
            list = list.stream()
                    .filter(products -> products.getHeight() <= Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 6) {
            int inputBetween = InputUtil.requireNum("Enter second value");
            list = list.stream()
                    .filter(products -> products.getHeight() > Float.parseFloat(inputValue))
                    .filter(products -> products.getHeight() < inputBetween)
                    .collect(Collectors.toList());
        }

        return list;
    }

    @Override
    public List<Products> filterWidth(Path path) throws IOException {
        MenuUtil.filterMethodText();
        int inputFilterMethod = InputUtil.requireNum("Select");

        List<Products> list = Files.lines(path)
                .skip(1)
                .map(CSVFileStreamUtil::getProducts)
                .collect(Collectors.toList());
        String inputValue = InputUtil.requireText("Enter value");

        if (inputFilterMethod == 1) {
            list = list.stream()
                    .filter(products -> products.getWidth().equals(Float.parseFloat(inputValue)))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 2) {
            list = list.stream()
                    .filter(products -> products.getWidth() > Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 3) {
            list = list.stream()
                    .filter(products -> products.getWidth() < Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 4) {
            list = list.stream()
                    .filter(products -> products.getWidth() >= Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 5) {
            list = list.stream()
                    .filter(products -> products.getWidth() <= Float.parseFloat(inputValue))
                    .collect(Collectors.toList());
        } else if (inputFilterMethod == 6) {
            int inputBetween = InputUtil.requireNum("Enter second value");
            list = list.stream()
                    .filter(products -> products.getWidth() > Float.parseFloat(inputValue))
                    .filter(products -> products.getWidth() < inputBetween)
                    .collect(Collectors.toList());
        }

        return list;
    }


}
