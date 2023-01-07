package service.inter;

import entity.Products;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface ProductServiceInter {
    void listRandomlySelectedEntities(Path path) throws IOException;

    void listTopEntities(Path path) throws IOException;

    void listBottomEntities(Path path) throws IOException;

    void listFields(Path path) throws IOException;

    List<Products> sortASC(Path path) throws IOException;

    List<Products> sortDESC(Path path) throws IOException;

    List<Products> searchTheEntites(Path path, int selectedField, String inputValue) throws IOException;

    void columnNames(Path path);

    List<Products> filterEntities(Path path) throws IOException;

    List<Products> filterId(Path path) throws IOException;

    List<Products> filterItemId(Path path) throws IOException;

    List<Products> filterPrice(Path path) throws IOException;

    List<Products> filterOldPrice(Path path) throws IOException;

    List<Products> filterDepth(Path path) throws IOException;

    List<Products> filterHeight(Path path) throws IOException;

    List<Products> filterWidth(Path path) throws IOException;
}
