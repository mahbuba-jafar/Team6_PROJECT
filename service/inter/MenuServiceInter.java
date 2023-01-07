package service.inter;


import java.io.IOException;
import java.nio.file.Path;

public interface MenuServiceInter {

    void getSectionOne(Path path) throws IOException;
    void getSectionTwo(Path path) throws IOException;
    void getSectionThree(Path path) throws IOException;
    void getSectionFour(Path path) throws IOException;
    void getSectionFive(Path path) throws IOException;
}
