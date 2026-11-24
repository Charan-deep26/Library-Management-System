public class Staff extends Member{
    public Staff(String id,String name){
        super(id,name);
    }
    @Override
    public String getType(){
        return "Staff";
    }
    @Override
    public int getLoanDays(){
        return 21;
    }
    @Override 
    public int getMaxItemsAtOnce(){
        return 4;
    }
}
