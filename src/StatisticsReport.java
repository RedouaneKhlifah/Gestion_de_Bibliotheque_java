public class StatisticsReport {
    private final int TotalBooks;
    private final int AvailableBooks;
    private final int BorrowedBooks;
    private final int LostBooks;

    public StatisticsReport(int TotalBooks,int AvailableBooks,int BorrowedBooks,int LostBooks){
        this.TotalBooks = TotalBooks;
        this.AvailableBooks = AvailableBooks;
        this.BorrowedBooks = BorrowedBooks;
        this.LostBooks = LostBooks;
    }

    public int getTotalBooks(){
        return  this.TotalBooks;
    }
    public int getAvailableBooks(){
        return  this.AvailableBooks;
    }
    public int getBorrowedBooks(){
        return  this.BorrowedBooks;
    }
    public int  getLostBooks(){
        return  this.LostBooks;
    }
}
