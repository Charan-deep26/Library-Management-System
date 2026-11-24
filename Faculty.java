public class Faculty extends Member{
    public Faculty(String id,String name){
        super(id,name);
    }
    @Override
    public String getType(){
        return "Faculty";
    }
    @Override
    public int getLoanDays(){
        return 30;
    }
    @Override 
    public int getMaxItemsAtOnce(){
        return 5;
    }
}
