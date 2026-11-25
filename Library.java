import java.util.*;

public class Library {
    private Map<String,LibraryItem>items;
    private Map<String,Member> members;
    private Map<String,Loan> loans;
    private int today;
    Map<String,Queue<Member>>reservations;

    Library(){
        this.items=new HashMap<>();
        this.members=new HashMap<>();
        this.loans=new HashMap<>();
        this.today=0;
    }
    public int getToday(){
        return today;
    }
    public boolean isItemBorrowed(String itemId){
        return loans.containsKey(itemId);
    }

    public void addItem(LibraryItem item){
        items.put(item.getId(),item);
    }

    public void addMember(Member member){
        members.put(member.getId(),member);
    }

    private int countLoans(String memberId){
        int n=0;
        for(Loan l:loans.values()){
            if(l.getMember().getId().equals(memberId)){
                n++;
            }
        }
        return n;
    }

    public String getItemStatus(LibraryItem item) {
        if (!(item instanceof Borrowable)) {
            return "NOT_BORROWABLE";
        }
        Loan loan = loans.get(item.getId());
        if (loan != null) {
            return "ON_LOAN|" + loan.getMember().getId();
        }
        return "AVAILABLE";
    }

    public int borrow(String memberId,String ItemId) throws LibraryException{
            Member member=findMember(memberId); //throws member not found
            LibraryItem item=findItem(ItemId); //throws itemnotfound
            boolean flag=isItemBorrowed(item.getId());
            if(flag){
                throw new ItemNotAvailableException(item.getId()+" is on loan");
            }
            if(!(item instanceof Borrowable)){
                throw new ItemNotAvailableException(item.getId()+" cannot be borrowed");
            }
            if(countLoans(member.getId())>=member.getMaxItemsAtOnce()){
                throw new BorrowLimitExceededException(member.getId()+" has reached the limit of "+member.getMaxItemsAtOnce());
            }
            loans.put(item.getId(),new Loan(item,member,today));
            int due=today+member.getLoanDays();
            return due;
    }

    public LibraryItem findItem(String id) throws LibraryException{
        LibraryItem item = items.get(id);
        if(item==null){
            throw new ItemNotFoundException("item "+id+" not found");
        }
        return item;
    }

    public Member findMember(String id) throws LibraryException{
        Member member = members.get(id);
        if(member==null){
            throw new MemberNotFoundException("member "+id+" not found");
        }
        return member;
    }
    
    public int returnItem(String itemId) throws LibraryException{
            LibraryItem item=items.get(itemId);
            if(item==null){
                throw new ItemNotFoundException("item "+itemId+" not found");
            }
            Loan loan =loans.get(itemId);
            if(loan==null){
                throw new ItemNotOnLoanException(itemId+" is not on loan");
            }
            int fine = loan.calcFine(today);
            loans.remove(itemId);
            return fine;
    }

    public int advanceDays(int days){
        today+=days;
        return today;
    }

    public List<LibraryItem> search(String text){
        List<LibraryItem>result= new ArrayList<>();
        text=text.toLowerCase();
        for(LibraryItem item : items.values()){
            if (item.getTitle().toLowerCase().contains(text)) {
                result.add(item);
            }
        }
        result.sort(Comparator.comparing(LibraryItem::getTitle));
        return result;
    }

    public List<Loan> getLoans(String memberId) throws MemberNotFoundException{
        if(members.get(memberId)==null){ 
            throw new MemberNotFoundException("member "+memberId+" not found");
        }
        List<Loan> result = new ArrayList<>();
        for(Loan loan : loans.values()){
            if(loan.getMember().getId().equals(memberId)){
                result.add(loan);
            }
        }
        result.sort(Comparator.comparing(Loan::getDueDay).thenComparing(l -> l.getItem().getId()));
        return result;
    }

    public List<Loan> getOverdue(){
        List<Loan> result = new ArrayList<>();
        for(Loan loan: loans.values()){
            if(loan.getDaysLate(today)>0){
                result.add(loan);
            }
        }
        result.sort(
            Comparator.comparing(Loan::getDueDay)
              .reversed()
              .thenComparing(l -> l.getItem().getId())
            );
            return result;
    }

    public List<LibraryItem> getInventory(){
        List<LibraryItem> result = new ArrayList<>();
        for(LibraryItem item: items.values()){
            result.add(item);
        }
        result.sort(Comparator.comparing(LibraryItem::getId));
        return result;
    }

}
