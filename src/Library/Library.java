package Library;

import java.sql.Connection;

import Book.TransactionManager;
import Database.Database;
import Book.BookManager;

public class Library  {

    private final Database database = new Database();
    private final Connection connection = database.getConnection();
    private final BookManager bookManager = new BookManager(connection);
    private final TransactionManager transactionManager = new TransactionManager(connection);
    public int addBook(){
        return bookManager.addBookForm("Add Book");
    }
    public void updateBook(){
        bookManager.updateForm("Update Book");
    }

    public void deleteBook(){
        bookManager.deleteForm("Delete Book");
    }

    public void BorrowBook(){
    transactionManager.BorrowBookForm("Borrow Book");
    }

    public void ReturnBook(){
        transactionManager.ReturnBookForm("Borrow Book");
    }
}
