public abstract class Member {
    private String id;
    private String name;
    public Member(String id,String name){
        this.id=id;
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public abstract String getType();
    public String getId(){
        return id;
    }
    public abstract int getLoanDays();
    public abstract int getMaxItemsAtOnce();
}
