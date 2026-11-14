import java.time.LocalDate;
import java.util.Scanner;

// Week 8 - Inheritance, method overriding and runtime polymorphism
// Every problem has a base class with one method that each child class overrides,
// and the main flow just loops over an array of the base type (no if-else on type).
public class Week8Problems {

    // ---------- Problem 1: Canteen Billing Counter ----------
    static abstract class Customer {
        String type;
        double amount;

        Customer(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        abstract double finalAmount();
    }

    static class StudentCustomer extends Customer {
        StudentCustomer(double amount) { super("STUDENT", amount); }
        double finalAmount() { return amount - amount * 0.10; }
    }

    static class StaffCustomer extends Customer {
        StaffCustomer(double amount) { super("STAFF", amount); }
        double finalAmount() { return amount - amount * 0.05; }
    }

    static class GuestCustomer extends Customer {
        GuestCustomer(double amount) { super("GUEST", amount); }
        double finalAmount() { return amount + 10; }
    }

    static void canteen(Scanner sc) {
        int n = sc.nextInt();
        Customer[] bills = new Customer[n];
        for (int i = 0; i < n; i++) {
            String t = sc.next();
            double amt = sc.nextDouble();
            if (t.equals("STUDENT")) bills[i] = new StudentCustomer(amt);
            else if (t.equals("STAFF")) bills[i] = new StaffCustomer(amt);
            else bills[i] = new GuestCustomer(amt);
        }
        double total = 0;
        for (int i = 0; i < n; i++) {
            double f = bills[i].finalAmount();
            System.out.printf("%s: %.2f%n", bills[i].type, f);
            total += f;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    // ---------- Problem 2: Campus Parking Charge Calculator ----------
    static abstract class Vehicle {
        String type;
        int hours;

        Vehicle(String type, int hours) {
            this.type = type;
            this.hours = hours;
        }

        abstract double charge();
    }

    static class Bike extends Vehicle {
        Bike(int hours) { super("BIKE", hours); }
        double charge() { return 10.0 * hours; }
    }

    static class Car extends Vehicle {
        Car(int hours) { super("CAR", hours); }
        double charge() { return 30.0 + 20.0 * (hours - 1); }
    }

    static class Truck extends Vehicle {
        Truck(int hours) { super("TRUCK", hours); }
        double charge() {
            double c = 50.0 * hours;
            if (c < 100) c = 100;
            return c;
        }
    }

    static void parking(Scanner sc) {
        int n = sc.nextInt();
        Vehicle[] list = new Vehicle[n];
        for (int i = 0; i < n; i++) {
            String t = sc.next();
            int h = sc.nextInt();
            if (t.equals("BIKE")) list[i] = new Bike(h);
            else if (t.equals("CAR")) list[i] = new Car(h);
            else list[i] = new Truck(h);
        }
        double total = 0;
        for (int i = 0; i < n; i++) {
            double c = list[i].charge();
            System.out.printf("%s: %.2f%n", list[i].type, c);
            total += c;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    // ---------- Problem 3: Hostel Electricity Bill ----------
    static abstract class Room {
        String type;
        int units;

        Room(String type, int units) {
            this.type = type;
            this.units = units;
        }

        abstract double bill();
    }

    static class SingleRoom extends Room {
        SingleRoom(int units) { super("SINGLE", units); }
        double bill() { return 8.0 * units; }
    }

    static class SharedRoom extends Room {
        int occupants;   // the extra value only a shared room needs

        SharedRoom(int units, int occupants) {
            super("SHARED", units);
            this.occupants = occupants;
        }

        double bill() { return (6.0 * units) / occupants; }
    }

    static class ACRoom extends Room {
        ACRoom(int units) { super("AC", units); }
        double bill() { return 10.0 * units + 200; }
    }

    static void hostel(Scanner sc) {
        int n = sc.nextInt();
        Room[] rooms = new Room[n];
        for (int i = 0; i < n; i++) {
            String t = sc.next();
            int u = sc.nextInt();
            if (t.equals("SINGLE")) {
                rooms[i] = new SingleRoom(u);
            } else if (t.equals("SHARED")) {
                int occ = sc.nextInt();
                rooms[i] = new SharedRoom(u, occ);
            } else {
                rooms[i] = new ACRoom(u);
            }
        }
        double total = 0;
        for (int i = 0; i < n; i++) {
            double b = rooms[i].bill();
            System.out.printf("%s: %.2f%n", rooms[i].type, b);
            total += b;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    // ---------- Problem 4: Festival Bonus Calculator ----------
    static abstract class Employee {
        String name;
        double salary;

        Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        abstract double bonus();
    }

    static class FullTime extends Employee {
        FullTime(String name, double salary) { super(name, salary); }
        double bonus() { return salary * 0.10; }
    }

    static class PartTime extends Employee {
        PartTime(String name, double salary) { super(name, salary); }
        double bonus() { return salary * 0.05; }
    }

    static class Intern extends Employee {
        Intern(String name, double salary) { super(name, salary); }
        double bonus() { return 2000; }
    }

    static void bonus(Scanner sc) {
        int n = sc.nextInt();
        Employee[] staff = new Employee[n];
        for (int i = 0; i < n; i++) {
            String t = sc.next();
            String name = sc.next();
            double sal = sc.nextDouble();
            if (t.equals("FULLTIME")) staff[i] = new FullTime(name, sal);
            else if (t.equals("PARTTIME")) staff[i] = new PartTime(name, sal);
            else staff[i] = new Intern(name, sal);
        }
        double total = 0;
        for (int i = 0; i < n; i++) {
            double b = staff[i].bonus();
            System.out.printf("%s: %.2f%n", staff[i].name, b);
            total += b;
        }
        System.out.printf("Total Bonus: %.2f%n", total);
    }

    // ---------- Problem 5: Streaming Plan Renewal Reminder ----------
    static abstract class Plan {
        String name;
        LocalDate start;

        Plan(String name, LocalDate start) {
            this.name = name;
            this.start = start;
        }

        abstract int validityDays();

        LocalDate renewalDate() {
            return start.plusDays(validityDays());
        }
    }

    static class BasicPlan extends Plan {
        BasicPlan(String name, LocalDate start) { super(name, start); }
        int validityDays() { return 30; }
    }

    static class StandardPlan extends Plan {
        StandardPlan(String name, LocalDate start) { super(name, start); }
        int validityDays() { return 90; }
    }

    static class PremiumPlan extends Plan {
        PremiumPlan(String name, LocalDate start) { super(name, start); }
        int validityDays() { return 365; }
    }

    static void streaming(Scanner sc) {
        int n = sc.nextInt();
        Plan[] subs = new Plan[n];
        for (int i = 0; i < n; i++) {
            String t = sc.next();
            String name = sc.next();
            LocalDate d = LocalDate.parse(sc.next());
            if (t.equals("BASIC")) subs[i] = new BasicPlan(name, d);
            else if (t.equals("STANDARD")) subs[i] = new StandardPlan(name, d);
            else subs[i] = new PremiumPlan(name, d);
        }
        for (int i = 0; i < n; i++) {
            System.out.println(subs[i].name + ": " + subs[i].renewalDate());
        }
    }

    // ---------- main: runs each problem on its sample input ----------
    public static void main(String[] args) {
        System.out.println("--- Problem 1 ---");
        canteen(new Scanner("3\nSTUDENT 200\nSTAFF 300\nGUEST 150\n"));

        System.out.println("--- Problem 2 ---");
        parking(new Scanner("4\nBIKE 3\nCAR 4\nTRUCK 1\nCAR 1\n"));

        System.out.println("--- Problem 3 ---");
        hostel(new Scanner("3\nSINGLE 120\nSHARED 150 3\nAC 100\n"));

        System.out.println("--- Problem 4 ---");
        bonus(new Scanner("3\nFULLTIME Asha 50000\nPARTTIME Ravi 30000\nINTERN Neha 15000\n"));

        System.out.println("--- Problem 5 ---");
        streaming(new Scanner("4\nBASIC Asha 2024-01-15\nSTANDARD Ravi 2024-02-01\nPREMIUM Neha 2024-03-10\nBASIC Kiran 2024-12-20\n"));
    }
}
