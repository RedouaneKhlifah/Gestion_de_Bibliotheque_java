package Library;
import Helpers.MenuHandler;
import Database.Database;
import Helpers.Message;
import Helpers.StylingText;


public class LibrarySetup {
    public static Library initializeLibrary() {
        Library library = new Library();

        // main menu options
        String[] MainMenu = new String[]{"Book Manager", "Transaction Manager", "Statistics Report"};

        // sub Menu options
        String[] bookManagerOptions = new String[]{"Add Book", "Update Book", "Delete Book", "Back Home"};
        String[] TransactionManagerOptions = new String[]{"Borrow a book","Return Book","Back home"};
        String[] StatisticsReportOptions = new String[]{"Show all books"," Show Available books "," Show Borrowed books","Show Lost books","Back home"};


        boolean exit = false;

        while (!exit) {
            MenuHandler.displayMenu("Main Menu", MainMenu);
            int choice = MenuHandler.getUserChoice();


            switch (choice) {
                case 1 -> {
                    MenuHandler.displayMenu("Book Manager", bookManagerOptions);
                    int bookManagerChoice = MenuHandler.getUserChoice(); // Inner switch-case
                    switch (bookManagerChoice) {
                        // Handle sub-menu choices here
                        case 1 -> {

                            bookManagerChoice = library.addBook();


                            switch (bookManagerChoice) {
                                case 1 -> {
                                    Message.SuccessMessage("book added successfully");
                                    break;

                                }case 0 -> {
                                    break;
                                }
                                default -> {
                                    MenuHandler.displayError();
                                    break;
                                }
                            }


                        }
                        case 2 -> {
                            library.updateBook();
                        }

                        case 3 -> {
                            library.deleteBook();
                        }
                        // Add more cases for sub-menu choices
                        default -> {
                            // Handle invalid sub-menu choice
                        }
                    }
                }
                case 2 ->{
                    MenuHandler.displayMenu("Transaction Manager", TransactionManagerOptions);
                    int TransactionManager = MenuHandler.getUserChoice(); // Inner switch-case
                    switch (TransactionManager){
                        case 1 -> {
                            library.BorrowBook();
                        }case 2 -> {
                            library.ReturnBook();
                        }
                    }
                }
                case 3 -> MenuHandler.displayMenu("Statistics Report", StatisticsReportOptions);
                case 0 ->
                    // Exit the loop when the user chooses '0'
                        exit = true;
                default -> MenuHandler.displayError();
            }
        }
        return library;
    }
}
