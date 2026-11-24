public class Student extends Member{
    public Student(String id,String name){
        super(id,name);
    }
    @Override
    public String getType(){
        return "Student";
    }
    @Override
    public int getLoanDays(){
        return 14;
    }
    @Override 
    public int getMaxItemsAtOnce(){
        return 3;
    }
}
