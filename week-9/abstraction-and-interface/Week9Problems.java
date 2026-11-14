// Week 9 - OOP Fundamental (Abstraction & Interface) - Assignment Problems 1 to 5
// Each problem reads its input like the question says (first line N, then N lines).
// To test, main() feeds the sample input as text to a Scanner.

import java.util.Scanner;

// ---------------------------------------------------------------
// Problem 1: Movie Ticket Counter
// Abstract class: the convenience fee (20) is written ONCE here.
// Each seat type only gives its own price.
// ---------------------------------------------------------------
abstract class Seat {
    static final double CONVENIENCE_FEE = 20;

    abstract double price();

    double ticketAmount() {
        return price() + CONVENIENCE_FEE;
    }
}

class RegularSeat extends Seat {
    double price() { return 150; }
}

class PremiumSeat extends Seat {
    double price() { return 250; }
}

class ReclinerSeat extends Seat {
    double price() { return 400; }
}

// ---------------------------------------------------------------
// Problem 2: Parcel Shipping Desk
// Abstract class Parcel -> every parcel has a charge.
// Interface Insurable -> only express and fragile parcels can be insured,
// so standard parcels do not implement it.
// ---------------------------------------------------------------
interface Insurable {
    double insurance(double declaredValue);
}

abstract class Parcel {
    double weight;

    Parcel(double weight) { this.weight = weight; }

    abstract double charge();
}

class StandardParcel extends Parcel {
    StandardParcel(double w) { super(w); }
    double charge() { return 40 + 10 * weight; }
}

class ExpressParcel extends Parcel implements Insurable {
    ExpressParcel(double w) { super(w); }
    double charge() { return 80 + 15 * weight; }
    public double insurance(double declaredValue) { return declaredValue * 0.02; }
}

class FragileParcel extends Parcel implements Insurable {
    FragileParcel(double w) { super(w); }
    double charge() { return 40 + 10 * weight + 50; }       // standard charge + handling fee
    public double insurance(double declaredValue) { return declaredValue * 0.02; }
}

// ---------------------------------------------------------------
// Problem 3: College Fee Counter
// Abstract class Student -> name + tuition rule.
// Interface UsesBus -> the transport fee (12000) is kept in one place as a default method.
// ---------------------------------------------------------------
interface UsesBus {
    default double transportFee() { return 12000; }
}

abstract class Student {
    String name;

    Student(String name) { this.name = name; }

    abstract double tuition();

    double totalFee() {
        double fee = tuition();
        if (this instanceof UsesBus) {                        // ask "does this student use the bus?"
            fee += ((UsesBus) this).transportFee();
        }
        return fee;
    }
}

class DayScholar extends Student implements UsesBus {
    DayScholar(String n) { super(n); }
    double tuition() { return 40000; }
}

class Hosteller extends Student {
    Hosteller(String n) { super(n); }
    double tuition() { return 40000 + 60000; }                // tuition + hostel fee
}

class ScholarshipStudent extends Student implements UsesBus {
    ScholarshipStudent(String n) { super(n); }
    double tuition() { return 40000 / 2.0; }
}

// ---------------------------------------------------------------
// Problem 4: City Cab Fare Meter
// Abstract class Cab -> the minimum fare rule (never below 100) is written once.
// Interface NightService -> only sedan and SUV can offer it (adds 20%).
// ---------------------------------------------------------------
interface NightService {
    double withNightCharge(double fare);
}

abstract class Cab {
    abstract double ratePerKm();

    double fare(double km) {
        double f = km * ratePerKm();
        if (f < 100) {
            f = 100;
        }
        return f;
    }
}

class Mini extends Cab {
    double ratePerKm() { return 10; }
}

class Sedan extends Cab implements NightService {
    double ratePerKm() { return 14; }
    public double withNightCharge(double fare) { return fare * 1.20; }
}

class Suv extends Cab implements NightService {
    double ratePerKm() { return 18; }
    public double withNightCharge(double fare) { return fare * 1.20; }
}

// ---------------------------------------------------------------
// Problem 5: Home Appliance Energy Report
// Abstract class Appliance -> units = power * hours / 1000, cost = units * 8.
// Interface SaverMode -> only AC and washing machine support it (units reduced by 25%).
// The report asks "instanceof SaverMode" instead of a list of if-else on names.
// ---------------------------------------------------------------
interface SaverMode {
    double saverFactor();
}

abstract class Appliance {
    abstract double powerWatts();

    double units(double hours) {
        return powerWatts() * hours / 1000;
    }
}

class Fridge extends Appliance {
    double powerWatts() { return 150; }
}

class AirConditioner extends Appliance implements SaverMode {
    double powerWatts() { return 1500; }
    public double saverFactor() { return 0.75; }
}

class Tv extends Appliance {
    double powerWatts() { return 100; }
}

class WashingMachine extends Appliance implements SaverMode {
    double powerWatts() { return 500; }
    public double saverFactor() { return 0.75; }
}

public class Week9Problems {

    static void movieTickets(Scanner sc) {
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split(" ");
            int count = Integer.parseInt(p[1]);
            Seat seat;
            if (p[0].equals("REGULAR")) {
                seat = new RegularSeat();
            } else if (p[0].equals("PREMIUM")) {
                seat = new PremiumSeat();
            } else {
                seat = new ReclinerSeat();
            }
            double amount = seat.ticketAmount() * count;
            total += amount;
            System.out.printf("%s: %.2f%n", p[0], amount);
        }
        System.out.printf("Total: %.2f%n", total);
    }

    static void parcels(Scanner sc) {
        int n = Integer.parseInt(sc.nextLine().trim());
        double grand = 0;
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split(" ");
            double w = Double.parseDouble(p[1]);
            double value = Double.parseDouble(p[2]);
            Parcel parcel;
            if (p[0].equals("STANDARD")) {
                parcel = new StandardParcel(w);
            } else if (p[0].equals("EXPRESS")) {
                parcel = new ExpressParcel(w);
            } else {
                parcel = new FragileParcel(w);
            }
            double charge = parcel.charge();
            double ins = 0;
            if (parcel instanceof Insurable) {
                ins = ((Insurable) parcel).insurance(value);
            }
            double total = charge + ins;
            grand += total;
            System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f%n", p[0], charge, ins, total);
        }
        System.out.printf("Grand Total: %.2f%n", grand);
    }

    static void fees(Scanner sc) {
        int n = Integer.parseInt(sc.nextLine().trim());
        double collected = 0;
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split(" ");
            Student s;
            if (p[0].equals("DAY_SCHOLAR")) {
                s = new DayScholar(p[1]);
            } else if (p[0].equals("HOSTELLER")) {
                s = new Hosteller(p[1]);
            } else {
                s = new ScholarshipStudent(p[1]);
            }
            double fee = s.totalFee();
            collected += fee;
            System.out.printf("%s: %.2f%n", s.name, fee);
        }
        System.out.printf("Total Collected: %.2f%n", collected);
    }

    static void cabs(Scanner sc) {
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split(" ");
            double km = Double.parseDouble(p[1]);
            boolean night = p[2].equals("NIGHT");
            Cab cab;
            if (p[0].equals("MINI")) {
                cab = new Mini();
            } else if (p[0].equals("SEDAN")) {
                cab = new Sedan();
            } else {
                cab = new Suv();
            }
            double fare = cab.fare(km);
            if (night) {
                if (cab instanceof NightService) {
                    fare = ((NightService) cab).withNightCharge(fare);
                } else {
                    System.out.println(p[0] + ": night service not available");
                    continue;
                }
            }
            total += fare;
            System.out.printf("%s: %.2f%n", p[0], fare);
        }
        System.out.printf("Total: %.2f%n", total);
    }

    static void energy(Scanner sc) {
        int n = Integer.parseInt(sc.nextLine().trim());
        double totalCost = 0;
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split(" ");
            double hours = Double.parseDouble(p[1]);
            boolean saver = p.length > 2 && p[2].equals("SAVER");
            Appliance a;
            if (p[0].equals("FRIDGE")) {
                a = new Fridge();
            } else if (p[0].equals("AC")) {
                a = new AirConditioner();
            } else if (p[0].equals("TV")) {
                a = new Tv();
            } else {
                a = new WashingMachine();
            }
            double units = a.units(hours);
            if (saver) {
                if (a instanceof SaverMode) {
                    units = units * ((SaverMode) a).saverFactor();
                } else {
                    System.out.println(p[0] + ": saver mode not supported");
                    continue;
                }
            }
            double cost = units * 8;
            totalCost += cost;
            System.out.printf("%s: Units=%.2f Cost=%.2f%n", p[0], units, cost);
        }
        System.out.printf("Total Cost: %.2f%n", totalCost);
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 1 ---");
        movieTickets(new Scanner("3\nREGULAR 3\nPREMIUM 2\nRECLINER 1\n"));
        System.out.println("--- Problem 2 ---");
        parcels(new Scanner("3\nSTANDARD 3 500\nEXPRESS 2 1000\nFRAGILE 4 2000\n"));
        System.out.println("--- Problem 3 ---");
        fees(new Scanner("3\nDAY_SCHOLAR Asha\nHOSTELLER Ravi\nSCHOLAR Neha\n"));
        System.out.println("--- Problem 4 ---");
        cabs(new Scanner("4\nMINI 8 DAY\nSEDAN 10 NIGHT\nSUV 20 DAY\nMINI 5 NIGHT\n"));
        System.out.println("--- Problem 5 ---");
        energy(new Scanner("4\nFRIDGE 24\nAC 8 SAVER\nTV 5\nWASHER 2 SAVER\n"));
    }
}
