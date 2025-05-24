# Uçak Bilet Rezervasyon Konsol Uygulaması

Bu proje, Java dili kullanılarak nesneye dayalı programlama (OOP) prensiplerine uygun olarak geliştirilmiş basit bir uçak bileti rezervasyon sistemidir. Konsol arayüzü ile kullanıcıya uçuş bilgileri gösterilir, kullanıcı rezervasyon yapabilir ve rezervasyon bilgileri `.csv` dosyasına kaydedilir.

## Özellikler

- Uçak bilgileri tanımlanabilir (model, marka, seri no, koltuk kapasitesi).
- Lokasyon bilgileri tanımlanabilir (ülke, şehir, havaalanı).
- Uçuş bilgileri listelenebilir (lokasyon, saat, uçak).
- Kullanıcı rezervasyon yapabilir.
- Rezervasyonlar `.csv` dosyasına kaydedilir.

## Kullanılan Sınıflar

- **BaseEntity**: Ortak `id` ve `aktif` alanları içerir.
- **Ucak**: Uçak modeli, marka, seri numarası ve koltuk kapasitesi bilgilerini içerir.
- **Lokasyon**: Ülke, şehir ve havaalanı bilgilerini içerir.
- **Ucus**: Uçuş saatini, uçuş lokasyonunu ve kullanılan uçağı içerir.
- **Rezervasyon**: Kullanıcı adı, soyadı, yaşı ve seçilen uçuş bilgilerini içerir.
- **UcakRezervasyonUygulamasi**: Konsol arayüzünü çalıştıran ana sınıftır.

## Başlatmak için

1. Projeyi bir Java geliştirme ortamına (IDE) veya terminale aktarın.
2. `UcakRezervasyonUygulamasi.java` dosyasını derleyin ve çalıştırın:

```bash
javac UcakRezervasyonUygulamasi.java
java UcakRezervasyonUygulamasi
