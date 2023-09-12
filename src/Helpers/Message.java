package Helpers;

public class Message {
    static String greenColor = "\u001B[32m";
    static String resetFormatting = "\u001B[0m";
    public  static String SuccessMessage(String text){
        System.out.println(StylingText.centerText(greenColor+"----------------------------------------"+ resetFormatting));
        System.out.println( StylingText.centerText(greenColor+"|                                      |"+ resetFormatting));
        System.out.println(StylingText.centerText(greenColor +"|                                      |"+ resetFormatting));
        System.out.println(StylingText.centerText(greenColor+"|    "+ text +"       |"+resetFormatting));
        System.out.println(StylingText.centerText(greenColor+"|                                      |"+ resetFormatting));
        System.out.println(StylingText.centerText(greenColor+"|                                      |"+ resetFormatting));
        System.out.println(StylingText.centerText(greenColor+"----------------------------------------"+resetFormatting));
        System.out.println();
        System.out.println();
        return text;
    }
}
