import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Library library = new Library();
        while(sc.hasNextLine()){
            String command = sc.nextLine();
            String[] p=command.split("\\|");
            switch(p[0]){
                case "ADD_BOOK" -> {
                    library.addItem(new Book(p[1],p[2],p[3]));
                    System.out.println("OK");
                }
                case "ADD_MAGAZINE" -> {
                    library.addItem(new Magazine(p[1],p[2],p[3]));
                    System.out.println("OK");
                }
                case "ADD_REFERENCE" -> {
                    library.addItem(new ReferenceBook(p[1],p[2],p[3]));
                    System.out.println("OK");
                }
                case "ADD_MEMBER" -> {
                    if(p[3].equals("STUDENT")){
                        library.addMember(new Student(p[1],p[2]));
                        System.out.println("OK");
                    }
                    else if(p[3].equals("STAFF")){
                        library.addMember(new Staff(p[1],p[2]));
                        System.out.println("OK");
                    }
                    else if(p[3].equals("FACULTY")){
                        library.addMember(new Faculty(p[1],p[2]));
                        System.out.println("OK");
                    }
                }
                case "BORROW" -> {
                    try{
                        int due = library.borrow(p[1],p[2]);
                        System.out.println("OK due on day "+due);
                    }
                    catch(LibraryException e){
                        System.out.println("ERROR " + e.getMessage());
                    }
                }
                case "ADVANCE" ->{
                    int today = library.advanceDays(Integer.parseInt(p[1]));
                    System.out.println("OK today is day "+today);
                }
                case "RETURN" -> {
                    try{
                        int fine = library.returnItem(p[1]);
                        System.out.println("OK fine "+fine);
                    }
                    catch(LibraryException e){
                        System.out.println("ERROR " + e.getMessage());
                    }
                }
                case "LOANS" -> {
                    try{
                        List<Loan> loans=library.getLoans(p[1]);
                        for(Loan loan:loans){
                            System.out.println(loan.getItem().getId()+"|"+
                                loan.getItem().getTitle()+"|"+
                                loan.getDueDay()
                            );
                        }
                    }
                    catch(LibraryException e){
                        System.out.println("ERROR " + e.getMessage());
                    }
                }
                case "SEARCH" -> {
                    List<LibraryItem>items=library.search(p[1]);
                    for(LibraryItem item:items){
                        System.out.println(item.getId()+"|"+
                            item.getType()+"|"+
                            item.getTitle()
                        );
                    }
                }
                case "OVERDUE" -> {
                    List<Loan> loans = library.getOverdue();
                    for(Loan loan:loans){
                        System.out.println(loan.getItem().getId()+"|"+
                            loan.getMember().getId()+"|"+
                            loan.getDaysLate(library.getToday())+"|"+
                            loan.calcFine(library.getToday())
                        );
                    }
                }
                case "INVENTORY" -> {
                    List<LibraryItem>items = library.getInventory();
                    for(LibraryItem item:items){
                        System.out.println(item.getId()+"|"+
                            item.getType()+"|"+
                            library.getItemStatus(item)
                        );
                    }
                }
                
            }
        }
    }
}
