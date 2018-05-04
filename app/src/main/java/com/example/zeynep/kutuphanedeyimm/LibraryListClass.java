package com.example.zeynep.kutuphanedeyimm;

public class LibraryListClass {

    private String libraryName;
    private String description;
    private int libraryImage;

    private LibraryListClass(String libraryName, String description, int libraryImage) {
        this.libraryName = libraryName;
        this.description = description;
        this.libraryImage = libraryImage;
    }

    public static final LibraryListClass[] libraryList = {
            new LibraryListClass("Adile Sultan Halk Kütüphanesi", "Vakıf Mektep Sok. No: 13 34220 \\n Fatih/İstanbul ", R.drawable.adilesultan),
            new LibraryListClass("Ahmet Hamdi Tanpınar Edebiyat Müze Kütüphanesi", "Alemdar Mah., Alemdar Cad. Alay Köşkü, 34122 \\n Fatih/Istanbul", R.drawable.ahmethamdi),
            new LibraryListClass("Ahmet Kabaklı Kütüphanesi", "Mimar Sinan Mah. Akşemseddin Cad. No: 52 34091 \\n Fatih/İSTANBUL\\n Kitap Sayısı: 30.000 ", R.drawable.ahmetkabakli),
            new LibraryListClass("Atatürk Kitaplığı", "Gümüşsuyu Mahallesi, Miralay Şefik Bey Sok. No:6, 34437 \\n Beyoğlu/Istanbul ", R.drawable.ataturkitap),
            new LibraryListClass("Beyazıt Devlet Kütüphanesi", "Beyazıt Mh., Çadırcılar Cd. No:4, 34126 \\n Fatih/Istanbul \\n\" +\"Kitap Sayısı:1000 ", R.drawable.beyazitdevlet),
            new LibraryListClass("İSAM Kütüphanesi", "Altunizade Mahallesi, İcadiye Bağlarbaşı Cd. No:40, 34672 \\n Üsküdar/Istanbul ", R.drawable.isam),
            new LibraryListClass("İstanbul Modern Kütüphanesi", "Kılıçali Paşa Mahallesi, Meclis-i Mebusan Cad. No:4, 34433 \\n Beyoğlu/İstanbul ", R.drawable.modernkutup),
            new LibraryListClass("İstanbul Orhan Kemal İl Halk Kütüphanesi", "Mimar Kemalettin Mahallesi, Ordu Cd. No:33, 34130 \\n Fatih/Istanbul ", R.drawable.orhankemal),
            new LibraryListClass("İstanbul Sabahattin Zaim Üniversitesi Merkez Kütüphanesi", "Mimar Kemalettin Mahallesi, Ordu Cd. No:33, 34130 \\n Fatih/Istanbul ", R.drawable.izukutup),
            new LibraryListClass("Kadın Eserleri Kütüphanesi", "Balat Mahallesi, Kadir Has Kavşağı No:8, 34083 \\n Fatih/Istanbul ", R.drawable.kadineser),
            new LibraryListClass("Metin And Kütüphanesi", "Gültepe Mah. Bağlar Cad. No:61 \\n Sefaköy/İstanbul\\n Kitap Sayısı: 8.000 ", R.drawable.metinand),
            new LibraryListClass("Osman Akfırat Kütüphanesi", "Necmettin Erbakan Kültür Merkezi Gümüşsuyu Mah. Kelle İbrahim Cad. No:45 \\n Beykoz/İSTANBUL\\n\\n Kitap Sayısı: 14.000 ", R.drawable.osmanakfir),
            new LibraryListClass("Rasim Özdenören Kütüphanesi", "Başakşehir Kültür Merkezi Başakşehir Mah. Süleyman Çelebi Cad. Ilgaz Sok. No:4-A \\n Başakşehir/İstanbul\\n\\n Kitap Sayısı: 15.000 ", R.drawable.rasimozden),
    };

    public String getLibraryName() {
        return libraryName;
    }

    public String getDescription() {
        return description;
    }

    public int getLibraryImage() {
        return libraryImage;
    }

    public String toString() {
        return this.libraryName;
    }
}
