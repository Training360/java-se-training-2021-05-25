package trywithresources;

import java.util.Scanner;

public class ReadFile {

    public static void main(String[] args) {
//        // Java 9 előtt
//        try (Scanner scanner = new Scanner(ReadFile.class.getResourceAsStream("employees.txt"))) {
//            while (scanner.hasNextLine()) {
//                System.out.println(scanner.nextLine());
//            }
//        }

        Scanner scanner = new Scanner(ReadFile.class.getResourceAsStream("employees.txt"));
        try (scanner) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }
}
