package Helpers;

public class StylingText {
    public static String centerTextGreenUnderline(String title) {
        int consoleWidth = 146;

        int padding = (consoleWidth - title.length()) / 2;

        // ANSI escape codes for green color and underlined text
        String greenColor = "\u001B[32m";
        String underline = "\u001B[4m";
        String resetFormatting = "\u001B[0m";

        // Add padding spaces

        return " ".repeat(Math.max(0, padding)) +

                // Add green color and underline to the title
                greenColor + underline + title + resetFormatting;
    }

    public static String centerText(String title) {
        int consoleWidth = 146;
        int padding = (consoleWidth - title.length()) / 2;

        return " ".repeat(Math.max(0, padding)) + title;
    }

    public static String colorText(String color , String text){
       return "\u001B[31m" + text;
    }


}
