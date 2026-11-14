// Week 7 - Encapsulation and access control
// Each class keeps its data private and only exposes the methods the problem allows.

// ---------- Problem 1: The Health Bar ----------
class Character {
    private int health;
    private final int maxHealth;

    Character(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    void takeDamage(int amount) {
        int newValue = health - amount;
        if (newValue < 0) {
            newValue = 0;
        }
        health = newValue;
    }

    void heal(int amount) {
        int newValue = health + amount;
        if (newValue > maxHealth) {
            newValue = maxHealth;
        }
        health = newValue;
    }

    int getHealth() {
        return health;
    }
}

// ---------- Problem 2: The Playlist ----------
class Playlist {
    private String[] songs;
    private int count;

    Playlist(int maxSize) {
        songs = new String[maxSize];
        count = 0;
    }

    void addSong(String title) {
        if (count < songs.length) {
            songs[count] = title;
            count++;
        }
    }

    // gives back a brand new array so the caller can't touch the real one
    String[] getSongs() {
        String[] copy = new String[count];
        for (int i = 0; i < count; i++) {
            copy[i] = songs[i];
        }
        return copy;
    }

    int getSongCount() {
        return count;
    }
}

// ---------- Problem 3: The Password Checker ----------
class PasswordChecker {
    private final String password;

    PasswordChecker(String password) {
        this.password = password;
    }

    String getStrength() {
        int len = password.length();
        if (len < 6) {
            return "Weak";
        } else if (len < 10) {
            return "Medium";
        } else {
            return "Strong";
        }
    }
}

// ---------- Problem 4: The Traffic Light ----------
class TrafficLight {
    private final String id;
    private String color;

    TrafficLight(String id) {
        this.id = id;
        this.color = "RED";
    }

    String getColor() {
        return color;
    }

    String next() {
        if (color.equals("RED")) {
            color = "GREEN";
        } else if (color.equals("GREEN")) {
            color = "YELLOW";
        } else {
            color = "RED";
        }
        return color;
    }
}

// ---------- Problem 5: The Shopping Cart ----------
class Cart {
    private final String cartId;
    private int[] prices;
    private int itemCount;

    Cart(String cartId, int maxItems) {
        this.cartId = cartId;
        prices = new int[maxItems];
        itemCount = 0;
    }

    void addItem(int price) {
        if (itemCount < prices.length) {
            prices[itemCount] = price;
            itemCount++;
        }
    }

    int getTotal() {
        int total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += prices[i];
        }
        return total;
    }

    int getItemCount() {
        return itemCount;
    }
}

public class Week7Problems {
    public static void main(String[] args) {
        System.out.println("--- Problem 1: Health Bar ---");
        Character c = new Character(100);
        c.takeDamage(30);
        System.out.println("health = " + c.getHealth());
        c.heal(50);
        System.out.println("health = " + c.getHealth() + " (capped)");
        c.takeDamage(150);
        System.out.println("health = " + c.getHealth() + " (floored)");

        System.out.println("--- Problem 2: Playlist ---");
        Playlist p = new Playlist(10);
        p.addSong("Song A");
        p.addSong("Song B");
        String[] copy = p.getSongs();
        copy[0] = "Hacked";
        System.out.println("copy[0] = " + copy[0]);
        System.out.println("p.getSongs()[0] = " + p.getSongs()[0]);
        System.out.println("count = " + p.getSongCount());

        System.out.println("--- Problem 3: Password Checker ---");
        PasswordChecker pc = new PasswordChecker("abcd");
        System.out.println(pc.getStrength());
        PasswordChecker pc8 = new PasswordChecker("abcdefgh");
        System.out.println(pc8.getStrength());
        PasswordChecker pc2 = new PasswordChecker("abcdefghij");
        System.out.println(pc2.getStrength());
        PasswordChecker pc12 = new PasswordChecker("abcdefghijkl");
        System.out.println(pc12.getStrength());

        System.out.println("--- Problem 4: Traffic Light ---");
        TrafficLight t = new TrafficLight("TL-9");
        System.out.println(t.getColor());
        System.out.println(t.next());
        System.out.println(t.next());
        System.out.println(t.next());
        System.out.println(t.next());

        System.out.println("--- Problem 5: Shopping Cart ---");
        Cart cart = new Cart("CART-5", 20);
        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);
        System.out.println("total = " + cart.getTotal());
        System.out.println("count = " + cart.getItemCount());
    }
}
