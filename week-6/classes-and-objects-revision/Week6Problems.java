// Week 6 - Classes and Objects revision (M1 to M5)

// ---------- M1: Library Inventory Management ----------
class BookInventory {
    String title;
    String author;
    int copiesAvailable;

    BookInventory(String title, String author, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    void printEntry() {
        System.out.println(title + " by " + author + " - " + copiesAvailable + " copies available");
    }
}

// ---------- M2: Payroll Salary Management ----------
class PayrollAccount {
    private double basicSalary;
    private double bonus;

    PayrollAccount(double openingSalary) {
        if (openingSalary < 0) {
            System.out.println("Warning: negative salary given, starting at 0");
            basicSalary = 0;
        } else {
            basicSalary = openingSalary;
        }
        bonus = 0;
    }

    void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Bonus must be greater than 0");
            return;
        }
        bonus = bonus + amount;
        System.out.println("Bonus credited: Rs " + amount);
    }

    void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Tax percent must be between 0 and 100");
            return;
        }
        basicSalary = basicSalary - basicSalary * percent / 100;
        System.out.println("Tax deducted: " + (int) percent + "%");
    }

    double getNetSalary() {
        return basicSalary + bonus;
    }
}

// ---------- M3: Employee Profile Creation ----------
// (M3 and M5 both ask for a class called Employee, so each sits inside its own holder class)
class M3 {
    static class Employee {
        String empId;
        String empName;
        double salary;
        boolean isIntern;

        // permanent employee
        public Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
            this.isIntern = false;
        }

        // intern: chains to the constructor above with salary 0
        public Employee(String empId, String empName) {
            this(empId, empName, 0);
            this.isIntern = true;
        }

        void printProfile() {
            System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
        }
    }
}

// ---------- M4: Exam Hall Ticket Reference Management ----------
class HallTicket {
    String studentName;
    int seatNumber;

    HallTicket(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }
}

// ---------- M5: Employee and Company Information Management ----------
class M5 {
    static class Employee {
        String empName;
        double salary;
        static String companyName = "Bright Horizon Technologies";
        static int employeeCount = 0;

        Employee(String empName, double salary) {
            this.empName = empName;
            this.salary = salary;
            employeeCount++;
        }

        static void printCompanyInfo() {
            System.out.println(companyName);
            System.out.println("Employees on record: " + employeeCount);
        }
    }
}

public class Week6Problems {
    public static void main(String[] args) {
        System.out.println("--- M1: Library Inventory ---");
        BookInventory[] books = new BookInventory[4];
        books[0] = new BookInventory("Clean Code", "Robert C. Martin", 3);
        books[1] = new BookInventory("Effective Java", "Joshua Bloch", 5);
        books[2] = new BookInventory("Refactoring", "Martin Fowler", 0);
        books[3] = new BookInventory("Design Patterns", "GoF", 2);
        for (int i = 0; i < books.length; i++) {
            books[i].printEntry();
        }

        System.out.println("--- M2: Payroll ---");
        PayrollAccount acc = new PayrollAccount(50000);
        acc.creditBonus(5000);
        acc.deductTax(10);
        System.out.println("Net salary: Rs " + acc.getNetSalary());

        System.out.println("--- M3: Employee Profile ---");
        M3.Employee perm = new M3.Employee("E-101", "Divya", 65000);
        M3.Employee intern = new M3.Employee("E-102", "Arjun");
        perm.printProfile();
        intern.printProfile();

        System.out.println("--- M4: Hall Ticket References ---");
        HallTicket priya = new HallTicket("Priya", 0);
        HallTicket copy = priya;          // same object, not a new one
        copy.seatNumber = 45;
        System.out.println("Priya's seatNumber (via first variable):");
        System.out.println(priya.seatNumber);
        System.out.println("copy == priya: " + (copy == priya));
        HallTicket separate = new HallTicket("Priya", 45);
        System.out.println("separate == priya: " + (separate == priya));

        System.out.println("--- M5: Company Info ---");
        M5.Employee e1 = new M5.Employee("Asha", 40000);
        M5.Employee e2 = new M5.Employee("Ravi", 35000);
        M5.Employee e3 = new M5.Employee("Neha", 30000);
        M5.Employee.printCompanyInfo();
    }
}
