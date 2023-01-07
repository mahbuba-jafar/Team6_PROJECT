package util;

import java.util.Scanner;

public class InputUtil {
    public static String requireText(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(title + ":");
        String answer = scanner.nextLine();
        return answer;
    }

    public static int requireNum(String title) {
        Scanner sc=new Scanner(System.in);
        System.out.print(title+":");
        int answer=sc.nextInt();
        return answer;
    }
}
