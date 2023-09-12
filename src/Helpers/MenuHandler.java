package Helpers;

import Helpers.StylingText;

import java.util.Scanner;

public class MenuHandler extends StylingText {
        private static final Scanner scanner  = new Scanner(System.in);;

    public static void displayMenu(String title, String[] options) {

        System.out.println(centerTextGreenUnderline(title));
        System.out.println();

        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + " - " + options[i]);
            System.out.println();
        }


        System.out.println();
        System.out.print("Please choose an option: ");
    }

        public static int getUserChoice() {
            int choice = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    validInput = true;

                } catch (NumberFormatException e) {
                    displayError();
                }
            }
            validInput = false;
            return choice;

        }

        public static   void displayError(){
        System.out.print("please enter a valid option : ");
        }

    }


