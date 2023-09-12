package Book;

public class Book {
    private int Edition;
    private  String ISBN;
    private  String Title;
    private  String Author;
    private int Status;
    private  String borrowerMemberNumber;

    // constructor
    public Book (String ISBN ,String Title,String Author ){
        this.ISBN  = ISBN;
        this.Title  = Title;
        this.Author  = Author;
        this.Status = 0; // Default status
        this.borrowerMemberNumber = null; // No borrower by default
    }

    public String[] getIsbnAsArray() {
        return this.ISBN.trim().split(",");
    }
    public String getIsbn() {
        return  this.ISBN;
    }
    public String getTitle() {
        return  this.Title;
    }
    public String getAuthor() {
        return  this.Author;
    }

    public void setEdition(int edition) {
        Edition = edition;
    }

    public void setIsbn(String isbn) {
        this.ISBN = isbn;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }
    public int getEdition() {
        return this.Edition ;
    }
    public int getStatus() {
        return  this.Status;
    }
    public String getBorrowerMemberNumber() {
        return  this.borrowerMemberNumber;
    }


    public int setStatus(int status) {
        this.Status = status;
        return status;
    }

    public String setBorrowerMemberNumber(String borrowerMemberNumber) {
        this.borrowerMemberNumber = borrowerMemberNumber;
        return borrowerMemberNumber;
    }
}
