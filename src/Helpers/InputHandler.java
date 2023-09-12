package Helpers;

import java.util.Arrays;
import java.util.Scanner;
public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getStringData() {
        return scanner.nextLine();
    }
    public static String[] getArrayData() {
        String Data  = scanner.nextLine();
        return Data.trim().split(",");
    }

    public static String[] StringToArray(String string ) {
        return string.trim().split(",");
    }

    public static int confirmChoice(){
        System.out.println();
        System.out.println(StylingText.centerTextGreenUnderline("Options"));
        System.out.println();
        System.out.println("1 - confirm");
        System.out.println("0 - back home");
        System.out.println();
        System.out.print("Please choose an option: ");
        return MenuHandler.getUserChoice();
    }

}
