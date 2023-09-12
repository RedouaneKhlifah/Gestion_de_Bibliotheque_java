package Book;
import Helpers.StylingText ;
import Helpers.InputHandler;

import java.sql.Connection;

import Helpers.MenuHandler;
public class BookManager extends BookDatabaseRequest {

    public BookManager(Connection connection) {
        super(connection);
    }

    public Book findBookForm(String title) {
        System.out.println(StylingText.centerTextGreenUnderline(title));
        System.out.println();
        System.out.print("Book ISBN : ");
        String IsbnBook = InputHandler.getStringData();
        System.out.println(IsbnBook);
        System.out.println();
        System.out.println(StylingText.centerTextGreenUnderline("Options"));
        System.out.println();
        System.out.println("1 - Find book");
        System.out.println("0 - Back Home");
        System.out.println();
        System.out.print("Please choose an option: ");
        int choice = MenuHandler.getUserChoice();

        if (choice == 1) {
            Book foundBook = this.FindBookReq(IsbnBook);

            if (foundBook != null) {
                System.out.println(StylingText.centerTextGreenUnderline("Book"));
                System.out.println();
                System.out.println(StylingText.centerText(StylingText.colorText("\u001B[31m","is this is the right book ?")));
                System.out.println();
                System.out.println("Book Isbn : " + foundBook.getIsbn());
                System.out.println("Book Title : " + foundBook.getTitle());
                System.out.println("Book Author : " + foundBook.getAuthor());
                System.out.println();
                System.out.println();
                System.out.println(StylingText.centerTextGreenUnderline("Options"));
                System.out.println();
                System.out.println("1 - Yes");
                System.out.println("0 - Back Home");
                System.out.println();
                System.out.print("Please choose an option: ");
                choice = MenuHandler.getUserChoice();
                if (choice == 1) {
                    return foundBook;
                } else {
                    return null;
                }
            } else {
                System.out.println("Book not found.");

                return null;
            }
        } else {
            return null;
        }
    }

    public int addBookForm(String title){
        System.out.println();
        System.out.println(StylingText.centerTextGreenUnderline("book info"));
        System.out.println();
        System.out.print("Book title :   ");
        String titleBook  = InputHandler.getStringData();
        System.out.print("Book author :   ");
        String authorBook  = InputHandler.getStringData();
        System.out.print("please make sure to add ',' between each Isbn  :  ");
        String Isbn = InputHandler.getStringData();

        String[] IsbnArray = InputHandler.StringToArray(Isbn);

        System.out.println();
        System.out.println(StylingText.centerText(StylingText.colorText("\u001B[31m","are you sure you want to add this book ?")));


        System.out.println();

        System.out.println("          Book title : " + titleBook);
        System.out.println("          Book.Book author : " + authorBook);
        System.out.println("          ISBN  : ");
        for(int  i = 1 ; i <= IsbnArray.length ; i++){
            System.out.println("          " +i + " - "  + IsbnArray [i-1]);
        }

        System.out.println();
        int choice = InputHandler.confirmChoice();
        if (choice  == 1 ){
            Book book  = new Book(Isbn ,titleBook,authorBook );
            this.addBookReq(book);
            return choice;
        }
        return choice;

    }

    public void updateForm(String title){
        Book foundBook =  findBookForm(title);


        if(foundBook != null){
            System.out.println();
            System.out.println(StylingText.centerTextGreenUnderline("Options"));
            System.out.println();
            System.out.println("1  - update isbn ");
            System.out.println("2  - update title");
            System.out.println("3  - update author");
            System.out.println();
            System.out.print("Please choose an option: ");
            int choice = MenuHandler.getUserChoice();
            System.out.println();
           switch (choice){
               case 1 : {
                   System.out.print("new book isbn : ");
                   String bookIsbn = InputHandler.getStringData();
                   choice  = InputHandler.confirmChoice();
                   System.out.println();
                   if (choice == 1 ){
                       UpdateBookIsbnReq(foundBook ,bookIsbn);
                       break;
                   }else{
                       System.out.print("Please choose an valid option: ");
                   }
               } case 2 : {
                   System.out.print("new book title : ");
                   String bookTitle  = InputHandler.getStringData();
                   choice  = InputHandler.confirmChoice();
                   System.out.println();
                   if (choice == 1 ){
                       foundBook.setTitle(bookTitle);
                       UpdateBookTitleReq(foundBook);
                       break;
                   }else{
                       System.out.print("Please choose an valid option: ");
                   }
               } case 3  : {
                   System.out.print("new book author : ");
                   String BookAuthor  = InputHandler.getStringData();
                   choice  = InputHandler.confirmChoice();
                   System.out.println();
                   if (choice == 1 ){
                       foundBook.setAuthor(BookAuthor);
                       UpdateBookAuthorReq(foundBook);
                       break;
                   }else{
                       System.out.print("Please choose an valid option: ");
                   }
               }
           }

        }
    }
    public void deleteForm(String title ){
        Book foundBook =  findBookForm(title);
        if(foundBook != null) {
            System.out.println();
            System.out.println(StylingText.centerTextGreenUnderline("Delete Book"));
            System.out.println();
            System.out.println(StylingText.centerText(StylingText.colorText("\u001B[31m","are you sure you want to delete this book ?")));
            int choice = InputHandler.confirmChoice();
            System.out.println();
            if(choice == 1){
                DeleteBookCopyReq(foundBook);
            }
        }
    }
}
