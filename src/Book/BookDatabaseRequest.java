package Book;

import Helpers.Message;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BookDatabaseRequest {
    private Connection connection;

    public BookDatabaseRequest(Connection connection) {
        this.connection = connection;
    }
    public void scheduleOverdueBooksCheck(Connection connection) {
        this.connection = connection;

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Schedule the task to run every 24 hours (adjust as needed)
        executor.scheduleAtFixedRate(this::checkAndSetStatusForOverdueBooks, 0, 24, TimeUnit.HOURS);
    }

    private boolean checkAndSetStatusForOverdueBooks() {
        try(PreparedStatement LostBook = connection.prepareStatement("UPDATE Book_copy SET status = 2 WHERE DATE_ADD(timestamp_column, INTERVAL 7 DAY) <= NOW() AND status = 1;")){
            return true;
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
            return false;// Handle or log the exception appropriately
        }
    }

    /* Adding methods */
    public boolean addBookReq(Book book) {
        boolean check = false;
        String title = book.getTitle();
        String Author = book.getAuthor();
        String[] CopiesIsbn = book.getIsbnAsArray();

        int rowsAffected;

        try(PreparedStatement CheckBook  = connection.prepareStatement("Select * from book where Title = ? and Author = ?")) {
            CheckBook.setString(1, title);
            CheckBook.setString(2, Author);
            try (ResultSet resultSet = CheckBook.executeQuery()) {
                if (resultSet.next()) {
                    int bookID = resultSet.getInt("edition");
                    System.out.println("Book ID: " + bookID);

                    for (String isbn : CopiesIsbn) {
                        try (PreparedStatement Copy = connection.prepareStatement("INSERT INTO book_copy (isbn,edition) VALUES (?,?)")) {
                            Copy.setString(1, isbn);
                            Copy.setInt(2, bookID);
                            rowsAffected = Copy.executeUpdate();
                            check = rowsAffected >0;
                        }catch (SQLException e){
                            System.out.println("Error: " + e.getMessage()); // Handle or log the exception appropriately
                        }
                    }
                } else {
                    // Book not found
                    try (PreparedStatement Edition = connection.prepareStatement("INSERT INTO book (Title, Author) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                        Edition.setString(1, title);
                        Edition.setString(2, Author);

                        rowsAffected = Edition.executeUpdate();

                        if (rowsAffected > 0) {
                            // Retrieve the generated keys (IDs)
                            ResultSet generatedKeys = Edition.getGeneratedKeys();

                            if (generatedKeys.next()) {
                                // Retrieve the first generated key (ID)
                                int generatedID = generatedKeys.getInt(1);
                                for (String isbn : CopiesIsbn) {
                                    try (PreparedStatement Copy = connection.prepareStatement("INSERT INTO book_copy (isbn,edition) VALUES (?,?)")) {
                                        Copy.setString(1, isbn);
                                        Copy.setInt(2, generatedID);
                                        rowsAffected = Copy.executeUpdate();
                                        check = rowsAffected >0;
                                    }catch (SQLException e){
                                        System.out.println("Error: " + e.getMessage()); // Handle or log the exception appropriately
                                    }

                                }


                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage()); // Handle or log the exception appropriately
                    }
                }
            }catch (SQLException e) {
                System.out.println("Error: " + e.getMessage()); // Handle or log the exception appropriately
            }

        // Prepare and execute the SQL query to add a book to the database



    }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage()); // Handle or log the exception appropriately
        }
        return check;
    }


    /* Book search */
    public Book FindBookReq(String Isbn){
        try(PreparedStatement book  = connection.prepareStatement("SELECT * FROM book_copy INNER JOIN book ON book_copy.edition = book.edition WHERE book_copy.isbn = ?")){
            book.setString(1, Isbn);
            System.out.println(Isbn);
            try(ResultSet resultSet = book.executeQuery() ){
                if(resultSet.next()){
                    String FoundBookIsbn =  resultSet.getString("isbn");
                    String FoundBookTitle =  resultSet.getString("title");
                    String FoundBookAuthor =  resultSet.getString("author");
                    int FoundEdition =  resultSet.getInt("edition");
                    Book foundBook  =  new Book(FoundBookIsbn ,FoundBookTitle , FoundBookAuthor);
                    foundBook.setEdition(FoundEdition);
                    return foundBook;
                }
            }catch (SQLException e) {
                System.out.println("Error: " + e.getMessage()); // Handle or log the exception appropriately
            }

    }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage()); // Handle or log the exception appropriately
        }
        return null;
    }


    /* Update methods */
    public boolean UpdateBookTitleReq(Book book) {
        String BookTitle = book.getTitle();
        int BookEdition = book.getEdition();
        boolean check = false;

        try (PreparedStatement updatedBook = connection.prepareStatement("UPDATE book SET Title = ? WHERE edition = ?")) {
            updatedBook.setString(1, BookTitle);
            updatedBook.setInt(2, BookEdition);

            int rowsAffected = updatedBook.executeUpdate();

            if (rowsAffected > 0) {
                Message.SuccessMessage("book title updated successfully");
                check =  true;
            } else {
                System.out.println("Book title didn't updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage()); // Handle or log the exception appropriately
        }
        return check;
    }

    public boolean UpdateBookAuthorReq(Book book) {
        String BookAuthor = book.getAuthor();
        int BookEdition = book.getEdition();
        boolean check = false;

        try (PreparedStatement updatedBook = connection.prepareStatement("UPDATE book SET author = ? WHERE edition = ?")) {
            updatedBook.setString(1, BookAuthor);
            updatedBook.setInt(2, BookEdition);

            int rowsAffected = updatedBook.executeUpdate();

            if (rowsAffected > 0) {
                Message.SuccessMessage("book author updated successfully");
                check =  true;
            } else {
                System.out.println("Book title didn't updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage()); // Handle or log the exception appropriately
        }
        return check;
    }

    public boolean UpdateBookIsbnReq(Book book , String newIsbn) {
        String BookIsbn = book.getIsbn();
        boolean check = false;

        try (PreparedStatement updatedBook = connection.prepareStatement("UPDATE book_copy SET isbn = ? WHERE isbn = ?")) {
            updatedBook.setString(1, newIsbn);
            updatedBook.setString(2, BookIsbn);
            int rowsAffected = updatedBook.executeUpdate();

            if (rowsAffected > 0) {
                Message.SuccessMessage("book isbn updated successfully");
                check =  true;
            } else {
                System.out.println("Book isbn didn't updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage()); // Handle or log the exception appropriately
        }
        return check;
    }



    /* Delete m%ethods */
    public boolean DeleteBookCopyReq(Book book ){
        String BookIsbn = book.getIsbn();
        boolean check = false;
        try(PreparedStatement Book = connection.prepareStatement("DELETE FROM book_copy WHERE isbn = ?;")){
            Book.setString(1,BookIsbn);
            int rowsAffected = Book.executeUpdate();

            if (rowsAffected > 0) {
                Message.SuccessMessage("book deleted successfully");
                check =  true;}
            else {
                System.out.println("Book  didn't deleted successfully.");
            }
        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage()); // Handle or log the exception appropriately
        }
        return check;
    }

    public boolean  BorrowBook(Book book){
        boolean check = false;
        int Status = book.getStatus();
        String MemberNumber = book.getBorrowerMemberNumber();
        String Isbn = book.getIsbn();


        try(PreparedStatement BorrowedBook = connection.prepareStatement("update book_copy set status  = ? , borrowerMemberNumber = ? ,dateBorrowed = NOW()  where isbn = ?")){
            BorrowedBook.setInt(1,Status);
            BorrowedBook.setString(2,MemberNumber);
            BorrowedBook.setString(3,Isbn);
            int rowEffected = BorrowedBook.executeUpdate();
            check  = rowEffected > 0;
            return check;
        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage()); // Handle or log the exception appropriately
        }
        return check;
    }

    /*
    public void scheduleOverdueBooksCheck(Connection connection) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Schedule the task to run every 24 hours (adjust as needed)
        executor.scheduleAtFixedRate(() -> {
            checkAndSetStatusForOverdueBooks(connection);
        }, 0, 24, TimeUnit.HOURS);
    }

    private void checkAndSetStatusForOverdueBooks(Connection connection) {

    }
     */
    public boolean  ReturnBook(Book book){
        boolean check = false;
        int Status = book.getStatus();
        String MemberNumber = book.getBorrowerMemberNumber();
        String Isbn = book.getIsbn();


        try(PreparedStatement BorrowedBook = connection.prepareStatement("update book_copy set status  = ? , borrowerMemberNumber = ? ,dateBorrowed = NOW()  where isbn = ?")){
            BorrowedBook.setInt(0,Status);
            BorrowedBook.setString(2,MemberNumber);
            BorrowedBook.setString(3,Isbn);
            int rowEffected = BorrowedBook.executeUpdate();
            check  = rowEffected > 0;
            return check;
        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage()); // Handle or log the exception appropriately
        }
        return check;
    }

}

