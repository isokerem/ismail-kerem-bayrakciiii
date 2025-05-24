import java.io.*;
import java.util.*;

abstract class BaseEntity {
    UUID id = UUID.randomUUID();
    boolean aktif = true;
}

class Ucak extends BaseEntity {
    String model;
    String marka;
    String seriNo;
    int koltukKapasitesi;

    Ucak(String model, String marka, String seriNo, int koltukKapasitesi) {
        this.model = model;
        this.marka = marka;
        this.seriNo = seriNo;
        this.koltukKapasitesi = koltukKapasitesi;
    }

    public String toString() {
        return marka + " " + model + " (" + seriNo + ") - Kapasite: " + koltukKapasitesi;
    }
}

class Lokasyon extends BaseEntity {
    String ulke;
    String sehir;
    String havaalani;

    Lokasyon(String ulke, String sehir, String havaalani) {
        this.ulke = ulke;
        this.sehir = sehir;
        this.havaalani = havaalani;
    }

    public String toString() {
        return havaalani + " - " + sehir + ", " + ulke;
    }
}

class Ucus extends BaseEntity {
    Lokasyon lokasyon;
    String saat;
    Ucak ucak;
    List<Rezervasyon> rezervasyonlar = new ArrayList<>();

    Ucus(Lokasyon lokasyon, String saat, Ucak ucak) {
        this.lokasyon = lokasyon;
        this.saat = saat;
        this.ucak = ucak;
    }

    public boolean koltukMusait() {
        return rezervasyonlar.size() < ucak.koltukKapasitesi;
    }

    public void rezervasyonEkle(Rezervasyon r) {
        if (koltukMusait()) {
            rezervasyonlar.add(r);
        }
    }

    public String toString() {
        return "Ucus: " + lokasyon + ", Saat: " + saat + ", Ucak: " + ucak;
    }
}

class Rezervasyon extends BaseEntity {
    Ucus ucus;
    String ad;
    String soyad;
    int yas;

    Rezervasyon(Ucus ucus, String ad, String soyad, int yas) {
        this.ucus = ucus;
        this.ad = ad;
        this.soyad = soyad;
        this.yas = yas;
    }

    public String toString() {
        return ad + " " + soyad + ", Yas: " + yas + ", Ucus: [" + ucus + "]";
    }
}

public class UcakRezervasyonUygulamasi {
    static List<Ucak> ucaklar = new ArrayList<>();
    static List<Lokasyon> lokasyonlar = new ArrayList<>();
    static List<Ucus> ucuslar = new ArrayList<>();
    static List<Rezervasyon> rezervasyonlar = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        ucaklar.add(new Ucak("737", "Boeing", "SN123", 2));
        lokasyonlar.add(new Lokasyon("Turkiye", "Istanbul", "IST"));
        ucuslar.add(new Ucus(lokasyonlar.get(0), "12:00", ucaklar.get(0)));

        while (true) {
            System.out.println("1. Ucuslari Listele\n2. Rezervasyon Yap\n3. Rezervasyonlari Kaydet\n4. Cikis");
            String secim = scanner.nextLine();
            if (secim.equals("1")) {
                for (int i = 0; i < ucuslar.size(); i++) {
                    System.out.println(i + ". " + ucuslar.get(i));
                }
            } else if (secim.equals("2")) {
                System.out.print("Ucus numarasi secin: ");
                int ucusIndex = Integer.parseInt(scanner.nextLine());
                Ucus secilenUcus = ucuslar.get(ucusIndex);
                if (secilenUcus.koltukMusait()) {
                    System.out.print("Ad: ");
                    String ad = scanner.nextLine();
                    System.out.print("Soyad: ");
                    String soyad = scanner.nextLine();
                    System.out.print("Yas: ");
                    int yas = Integer.parseInt(scanner.nextLine());
                    Rezervasyon r = new Rezervasyon(secilenUcus, ad, soyad, yas);
                    secilenUcus.rezervasyonEkle(r);
                    rezervasyonlar.add(r);
                    System.out.println("Rezervasyon yapildi.");
                } else {
                    System.out.println("Koltuk dolu.");
                }
            } else if (secim.equals("3")) {
                kaydetCSV("rezervasyonlar.csv", rezervasyonlar);
                System.out.println("Rezervasyonlar kaydedildi.");
            } else if (secim.equals("4")) {
                break;
            }
        }
    }

    static void kaydetCSV(String dosyaAdi, List<Rezervasyon> liste) throws Exception {
        FileWriter fw = new FileWriter(dosyaAdi);
        for (Rezervasyon r : liste) {
            fw.write(r.ad + "," + r.soyad + "," + r.yas + "," + r.ucus.lokasyon.havaalani + "," + r.ucus.saat + "," + r.ucus.ucak.seriNo + "\n");
        }
        fw.close();
    }
}
