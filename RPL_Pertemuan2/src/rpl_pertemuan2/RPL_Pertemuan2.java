package rpl_pertemuan2;

class Hewan {
    // Encapsulation
    private String nama;
    
    // Constructor
    public Hewan(String nama) {
        this.nama = nama;
    }
    
    // Getter untuk nama
    public String getNama() {
        return nama;
    }
    
    // Method untuk suara hewan
    public void bersuara() {
        System.out.println("Hewan ini bersuara.");
    }
}

// Kelas turunan (inheritance)
class Kucing extends Hewan {
    // Constructor
    public Kucing(String nama) {
        super(nama);
    }
    
    // Polymorphism
    @Override
    public void bersuara() {
        System.out.println(getNama() + " berkata: Meow!");
    }
}

// Kelas turunan lainnya
class Anjing extends Hewan {
    // Constructor
    public Anjing(String nama) {
        super(nama);
    }
    
    // Polymorphism
    @Override
    public void bersuara() {
        System.out.println(getNama() + " berkata: Guk guk!");
    }
}

public class RPL_Pertemuan2 {

    public static void main(String[] args) {
        Hewan kucing = new Kucing("Kitty");
        Hewan anjing = new Anjing("Doggy");
        
        kucing.bersuara();
        anjing.bersuara();
    }
    
}
