classDiagram
    direction TB

    %% =========================
    %% INTERFACE
    %% =========================

    class Borrowable {
        <<interface>>
        +getFineRate() double
    }

    %% =========================
    %% LIBRARY ITEM HIERARCHY
    %% =========================

    class LibraryItem {
        <<abstract>>
        -String id
        -String title
        +LibraryItem(String id, String title)
        +getId() String
        +getTitle() String
        +getType() String
        +isBorrowable() boolean
    }

    class Book {
        -String author
        +Book(String id, String title, String author)
        +getFineRate() double
        +getType() String
        +getAuthor() String
    }

    class Magazine {
        -String issue
        +Magazine(String id, String title, String issue)
        +getFineRate() double
        +getType() String
        +getIssue() String
    }

    class ReferenceBook {
        -String author
        +ReferenceBook(String id, String title, String author)
        +getType() String
        +getAuthor() String
    }

    LibraryItem <|-- Book
    LibraryItem <|-- Magazine
    LibraryItem <|-- ReferenceBook

    Borrowable <|.. Book
    Borrowable <|.. Magazine

    %% =========================
    %% MEMBER HIERARCHY
    %% =========================

    class Member {
        <<abstract>>
        -String id
        -String name
        +Member(String id, String name)
        +getName() String
        +getType() String
        +getId() String
        +getLoanDays() int
        +getMaxItemsAtOnce() int
    }

    class Student {
        +Student(String id, String name)
        +getType() String
        +getLoanDays() int
        +getMaxItemsAtOnce() int
    }

    class Staff {
        +Staff(String id, String name)
        +getType() String
        +getLoanDays() int
        +getMaxItemsAtOnce() int
    }

    class Faculty {
        +Faculty(String id, String name)
        +getType() String
        +getLoanDays() int
        +getMaxItemsAtOnce() int
    }

    Member <|-- Student
    Member <|-- Staff
    Member <|-- Faculty

    %% =========================
    %% LOAN
    %% =========================

    class Loan {
        -int borrowDay
        -int dueDay
        -LibraryItem item
        -Member member
        +Loan(LibraryItem item, Member member, int borrowDay)
        +getDueDay() int
        +getDaysLate(int today) int
        +calcFine(int today) double
        +getBorrowedDay() int
        +getItem() LibraryItem
        +getMember() Member
    }

    %% =========================
    %% LIBRARY
    %% =========================

    class Library {
        -Map~String, LibraryItem~ items
        -Map~String, Member~ members
        -Map~String, Loan~ loans
        -int today
        -Map~String, Queue~Member~~ reservations
        +addItem(LibraryItem item) void
        +addMember(Member member) void
        +borrow(String memberId, String itemId) void
        +returnItem(String itemId) double
        +advanceDays(int days) void
        +search(String text) List~LibraryItem~
        +getLoans(String memberId) List~Loan~
        +getOverdue() List~Loan~
        +getInventory() List~LibraryItem~
        +reserve(String memberId, String itemId) void
        +findItem(String id) LibraryItem
        +findMember(String id) Member
    }

    %% Library manages its collections

    Library "1" o-- "0..*" LibraryItem : inventory
    Library "1" o-- "0..*" Member : registered members
    Library "1" o-- "0..*" Loan : active loans

    %% Each loan has exactly one item and one member

    Loan "0..*" --> "1" LibraryItem : item
    Loan "0..*" --> "1" Member : member

    %% An item can have at most one active loan
    %% A member can have multiple active loans

    %% =========================
    %% EXCEPTION HIERARCHY
    %% =========================

    class Exception {
        +Exception(String message)
    }

    class LibraryException {
        <<exception>>
        +LibraryException(String message)
    }

    class ItemNotFoundException {
        <<exception>>
        +ItemNotFoundException(String message)
    }

    class MemberNotFoundException {
        <<exception>>
        +MemberNotFoundException(String message)
    }

    class ItemNotAvailableException {
        <<exception>>
        +ItemNotAvailableException(String message)
    }

    class BorrowLimitExceededException {
        <<exception>>
        +BorrowLimitExceededException(String message)
    }

    class ItemNotOnLoanException {
        <<exception>>
        +ItemNotOnLoanException(String message)
    }

    class ReservationNotAllowedException {
        <<exception>>
        +ReservationNotAllowedException(String message)
    }

    Exception <|-- LibraryException

    LibraryException <|-- ItemNotFoundException
    LibraryException <|-- MemberNotFoundException
    LibraryException <|-- ItemNotAvailableException
    LibraryException <|-- BorrowLimitExceededException
    LibraryException <|-- ItemNotOnLoanException
    LibraryException <|-- ReservationNotAllowedException

    %% =========================
    %% EXCEPTION DEPENDENCIES
    %% =========================

    Library ..> ItemNotFoundException : throws
    Library ..> MemberNotFoundException : throws
    Library ..> ItemNotAvailableException : throws
    Library ..> BorrowLimitExceededException : throws
    Library ..> ItemNotOnLoanException : throws
    Library ..> ReservationNotAllowedException : throws

    %% =========================
    %% MAIN
    %% =========================

    class Main {
        +main()
        +main(String[] args) void
        +process(String line, Library lib) void
    }

    Main ..> Library : uses
    Main ..> LibraryException : catches
