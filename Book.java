public class Book extends LibraryItem implements Borrowable{
    private String author;
    public Book(String id,String title,String author){
        super(id,title);
        this.author=author;
    }
    @Override
    public String getType(){
        return "BOOK";
    }
    public String getAuthor(){
        return author;
    }
    @Override
    public int getFineRate(){
        return 2;
    }
    @Override 
    public boolean isBorrowable(){
        return true;
    }
}
