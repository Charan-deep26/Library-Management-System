public class ReferenceBook extends LibraryItem{
    private String author;
    public ReferenceBook(String id,String title,String author){
        super(id,title);
        this.author=author;
    }
    @Override
    public String getType(){
        return "REFERENCE";
    }
    public String getAuthor(){
        return author;
    }
    @Override 
    public boolean isBorrowable(){
        return false;
    }
}
