public class Loan {
    private int borrowDay;
    private int dueDay;
    private LibraryItem item;
    private Member member;
    public Loan(LibraryItem item,Member member,int borrowDay){
        this.item=item;
        this.member=member;
        this.borrowDay=borrowDay;
        this.dueDay=borrowDay + member.getLoanDays();
    }
    public int getDueDay(){
        return dueDay;
    }
    public int getDaysLate(int today){
        return Integer.max(0, today - dueDay);
    }
    public int calcFine(int today){
        //make sure that the item is borrowable before putting it in loan
        return this.getDaysLate(today) * ((Borrowable) item).getFineRate();
    }
    public int getBorrowedDay(){
        return borrowDay;
    }
    public LibraryItem getItem(){
        return item;
    }
    public Member getMember(){
        return member;
    }

}
