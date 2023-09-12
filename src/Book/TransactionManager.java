package Book;

import Helpers.InputHandler;
import Helpers.Message;
import Helpers.StylingText;

import java.sql.Connection;

public class TransactionManager extends BookManager {
    public TransactionManager(Connection connection) {
        super(connection);
    }


    public void BorrowBookForm(String title){
        Book foundBook =  this.findBookForm(title);
        if(foundBook != null){
            System.out.println();
            System.out.println(StylingText.centerTextGreenUnderline("Borrow Book"));
            System.out.println();
            System.out.print("add user Member number : ");
            String MemberNumber = InputHandler.getStringData();
            foundBook.setStatus(1);
            foundBook.setBorrowerMemberNumber(MemberNumber);
            this.BorrowBook(foundBook);
            if(this.BorrowBook(foundBook)){
                System.out.println();
                Message.SuccessMessage("book Borrowed successfully");
                System.out.println();
            }else{
                System.out.println();
                StylingText.colorText("\u001B[31m",Message.SuccessMessage("book didn't borrowed successfully"));
                System.out.println();
            }
        }
    }

    public void ReturnBookForm(String title){
        Book foundBook =  this.findBookForm(title);
        if(foundBook != null){
            System.out.println();
            System.out.println(StylingText.centerTextGreenUnderline("Return Book"));
            System.out.println();
            foundBook.setStatus(0);
            foundBook.setBorrowerMemberNumber(null);
            this.BorrowBook(foundBook);
            if(this.BorrowBook(foundBook)){
                System.out.println();
                Message.SuccessMessage("book returned successfully");
                System.out.println();
            }else{
                System.out.println();
                StylingText.colorText("\u001B[31m",Message.SuccessMessage("book didn't returned successfully"));
                System.out.println();
            }
        }
    }

}
