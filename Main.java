import java.util.Scanner;
import java.util.ArrayList;

// Abstract class Person (abstraction)
abstract class Person {
    protected String name;
    protected int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Abstract method
    public abstract void displayInfo();
}

// Class Member extends Person (inheritance, encapsulation)
class Member extends Person {
    private String memberId;

    public Member(String name, int age, String memberId) {
        super(name, age);
        this.memberId = memberId;
    }

    // Method to display member information (polymorphism)
    @Override
    public void displayInfo() {
        System.out.println("Member Name: " + name + ", Age: " + age + ", Member ID: " + memberId);
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}

// Class Buku (Book) with methods for managing books
class Buku {
    private String bookTitle;
    private String author;

    public Buku(String bookTitle, String author) {
        this.bookTitle = bookTitle;
        this.author = author;
    }

    public void displayBookInfo() {
        System.out.println("Book Title: " + bookTitle + ", Author: " + author);
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return author;
    }
}

// Class PeminjamanBuku (Book Borrowing) with methods for borrowing books
class PeminjamanBuku {
    private Member member;
    private Buku buku;

    public PeminjamanBuku(Member member, Buku buku) {
        this.member = member;
        this.buku = buku;
    }

    public void borrowBook() {
        System.out.println(member.getMemberId() + " has borrowed the book titled: " + buku.getBookTitle());
    }

    public void returnBook() {
        System.out.println(member.getMemberId() + " has returned the book titled: " + buku.getBookTitle());
    }
}

// Class DataPegawai (Employee Data) for handling employee details
class DataPegawai {
    private String employeeName;
    private String employeeId;

    public DataPegawai(String employeeName, String employeeId) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
    }

    public void displayEmployeeInfo() {
        System.out.println("Employee Name: " + employeeName + ", Employee ID: " + employeeId);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Member> members = new ArrayList<>();
        ArrayList<Buku> books = new ArrayList<>();
        ArrayList<DataPegawai> employees = new ArrayList<>();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Add Employee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();
                    System.out.print("Enter member age: ");
                    int memberAge = scanner.nextInt();
                    System.out.print("Enter member ID: ");
                    String memberId = scanner.next();
                    members.add(new Member(memberName, memberAge, memberId));
                    System.out.println("Member added successfully.");
                    break;

                case 2:
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    books.add(new Buku(bookTitle, author));
                    System.out.println("Book added successfully.");
                    break;

                case 3:
                    if (members.isEmpty()) {
                        System.out.println("No members available. Please add a member first.");
                        break;
                    }
                    if (books.isEmpty()) {
                        System.out.println("No books available. Please add a book first.");
                        break;
                    }

                    System.out.println("Choose a member by index: ");
                    for (int i = 0; i < members.size(); i++) {
                        System.out.println(i + ". " + members.get(i).getMemberId());
                    }
                    int memberIndex = scanner.nextInt();

                    if (memberIndex < 0 || memberIndex >= members.size()) {
                        System.out.println("Invalid member index.");
                        break;
                    }

                    System.out.println("Choose a book by index: ");
                    for (int i = 0; i < books.size(); i++) {
                        System.out.println(i + ". " + books.get(i).getBookTitle());
                    }
                    int bookIndex = scanner.nextInt();

                    if (bookIndex < 0 || bookIndex >= books.size()) {
                        System.out.println("Invalid book index.");
                        break;
                    }

                    PeminjamanBuku borrowing = new PeminjamanBuku(members.get(memberIndex), books.get(bookIndex));
                    borrowing.borrowBook();
                    break;

                case 4:
                    System.out.print("Enter employee name: ");
                    String employeeName = scanner.nextLine();
                    System.out.print("Enter employee ID: ");
                    String employeeId = scanner.nextLine();
                    employees.add(new DataPegawai(employeeName, employeeId));
                    System.out.println("Employee added successfully.");
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
