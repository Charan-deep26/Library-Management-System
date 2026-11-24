public class Book extends LibraryItem implements Borrowable{
    private String author;
    public Book(String id,String title,String author){
        super(id,title);
        this.author=author;
    }
    @Override
    public String getType(){
        return "Book";
    }
    public String getAuthor(){
        return author;
    }
    @Override
    public double getFineRate(){
        return 2;
    }
    @Override 
    public boolean isBorrowable(){
        return true;
    }
}
