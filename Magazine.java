public class Magazine extends LibraryItem{
    private String issue;
    public Magazine(String id,String title,String issue){
        super(id,title);
        this.issue=issue;
    }
    @Override
    public String getType(){
        return "Book";
    }
    public String getIssue(){
        return issue;
    }
    @Override
    public double getFineRate(){
        return 1;
    }
    @Override 
    public boolean isBorrowable(){
        return true;
    }
}
