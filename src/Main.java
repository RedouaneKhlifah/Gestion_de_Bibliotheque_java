import Library.Library;
import Library.LibrarySetup;

public class Main {
    public static void main(String[] args) {
        // Press Alt+Entrée with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");
        Library library = LibrarySetup.initializeLibrary();



        /* menuHandler.displayMenu("Book.Book Manager" , MainMenu);

        int choice  = menuHandler.getUserChoice();
        if (choice == 1){
            menuHandler.displayMenu("book Manager" ,bookManagerOptions );
            choice  = menuHandler.getUserChoice();
        }else if (choice == 2){
            menuHandler.displayMenu("Transaction Manager" , TransactionManagerOptions);
            choice  = menuHandler.getUserChoice();
        } else if (choice == 3) {
            menuHandler.displayMenu("Statistics Report" , StatisticsReportOptions);
            choice  = menuHandler.getUserChoice();
        }else {
            while(choice != 0){
                menuHandler.displayError();
                choice  = menuHandler.getUserChoice();
            }
            menuHandler.displayMenu("Book.Book Manager", MainMenu);
            choice  = menuHandler.getUserChoice();
        } */

/*
        if (option == 1 ){
            int numLinesToClear = 30; // You can adjust this as needed

            // Print empty lines to simulate clearing the console
            for (int i = 0; i < numLinesToClear; i++) {
                System.out.println();
            }


            System.out.println(textToCenter);
            System.out.println();

            System.out.println("1 - Book.Book manager");
            System.out.println();

            System.out.println("2 - Transaction Manager");
            System.out.println();
            System.out.println("3 - Statistics Report");

            System.out.println();
            System.out.println();
            System.out.print("Please choose an option: ");
            option = Integer.parseInt(scanner.nextLine());


        }
*/
        /* Reuse later
        Book.Book test = new Book.Book("213J6678", "POOR DAD POOR DAD", "Scr") ;
        Book.Book addedBook = library.addBook(test);
        // Access book properties and print them
        System.out.println("Book.Book Info:");
        System.out.println("ISBN: " + addedBook.getISBN());
        System.out.println("Title: " + addedBook.getTitle());
        System.out.println("Author: " + addedBook.getAuthor());
        System.out.println("Status: " + addedBook.getStatus());
        System.out.println("BorrowerMemberNumber: " + addedBook.getBorrowerMemberNumber());*/



    }
/*
    private static Library.Library getLibrary() {
        Database.Database database = new Database.Database();
        Library.Library library = new Library.Library(database);
        Helpers.MenuHandler menuHandler = new Helpers.MenuHandler();

        // main menu options
        String[] MainMenu = new String[]{"Book.Book Manager", "Transaction Manager", "Statistics Report"};

        // sub Menu options
        String[] bookManagerOptions = new String[]{"Add Book.Book", "Update Book.Book", "Delete Book.Book", "Back Home"};
        String[] TransactionManagerOptions = new String[]{"Borrow a book","Return Book.Book","Back home"};
        String[] StatisticsReportOptions = new String[]{"Show all books"," Show Available books "," Show Borrowed books","Show Lost books","Back home"};


        boolean exit = false;
        menuHandler.displayMenu("Main Menu", MainMenu);
        while (!exit) {

            int choice = menuHandler.getUserChoice();

            switch (choice) {
                case 1 -> {
                    menuHandler.displayMenu("Book.Book Manager", bookManagerOptions);
                    int bookManagerChoice = menuHandler.getUserChoice(); // Inner switch-case
                    switch (bookManagerChoice) {
                        // Handle sub-menu choices here
                        case 1 -> {
                            library.addBookForm("Add Book.Book");
                        }
                        case 2 -> {
                            // Handle 'Update Book.Book' choice
                        }
                        // Add more cases for sub-menu choices
                        default -> {
                            // Handle invalid sub-menu choice
                        }
                    }
                }
                case 2 -> menuHandler.displayMenu("Transaction Manager", TransactionManagerOptions);
                case 3 -> menuHandler.displayMenu("Statistics Report", StatisticsReportOptions);
                case 0 ->
                    // Exit the loop when the user chooses '0'
                        exit = true;
                default -> menuHandler.displayError();
            }
        }
        return library;
    }*/
}