public class Magazine extends LibraryItem implements Borrowable{
    private String issue;
    public Magazine(String id,String title,String issue){
        super(id,title);
        this.issue=issue;
    }
    @Override
    public String getType(){
        return "Magazine";
    }
    public String getIssue(){
        return issue;
    }
    @Override
    public int getFineRate(){
        return 1;
    }
    @Override 
    public boolean isBorrowable(){
        return true;
    }
}
