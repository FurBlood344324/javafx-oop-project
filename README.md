# Bağış Takip Sistemi

Bu proje, **JavaFX** kullanarak geliştirilmiş bir Bağış Takip Sistemi uygulamasıdır. Proje, bağışların ve bağışçıların takibini kolaylaştırmak için tasarlanmıştır.

## Proje Özeti
Bağış Takip Sistemi, bağış yapılan tutarların, bağışçılarla olan ilişkilerin ve genel bağış akışının yönetimini sağlamak için kullanıcı dostu bir arayüz sunar. 

## Kullanılan Teknolojiler
- **Java**: Uygulamanın temel programlama dili
- **JavaFX**: Kullanıcı arayüzü tasarımı
- **Scene Builder**: JavaFX FXML tasarımı için
- **SQLite** (veya başka bir veritabanı): Bağış verilerinin depolanması

## Özellikler
- Bağışçı ekleme, silme ve güncelleme
- Bağış kayıtlarının eklenmesi ve takibi
- Bağışçıların toplam bağış miktarını gösteren raporlama
- Basit ve sezgisel arayüz

## Kurulum

### Gerekli Araçlar
- Java Development Kit (JDK 11 veya üstü)
- JavaFX SDK
- IDE: IntelliJ IDEA, Eclipse veya NetBeans
- Scene Builder (opsiyonel, UI tasarımı için önerilir)

### Adımlar
1. **JavaFX SDK**'yı indirin ve projenize dahil edin.
2. Proje klasöründe gerekli bağımlılıkları ekleyin.
3. Projeyi IDE'nizde açın ve aşağıdaki gibi çalıştırın:

   ```bash
   java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -jar BagisTakipSistemi.jar
   ```

4. Veritabanını başlatmak için gerekli SQL scriptlerini çalıştırın (örnek olarak `database/init.sql` dosyası kullanılabilir).

## Kullanım
1. Programı başlattığınızda ana ekranda **Bağışçı Ekle** ve **Bağış Kayıtları** bölümleri bulunur.
2. Yeni bağışçılar eklemek için ilgili formu doldurup kaydedin.
3. Bağışları ekledikçe veritabanında güncel verileri görebilirsiniz.
4. Raporlama sekmesinden toplam bağışlar ve bağışçı detaylarına ulaşabilirsiniz.

## Gelecekteki Geliştirmeler
- Daha detaylı raporlama sistemi
- Bulut tabanlı veritabanı entegrasyonu
- E-posta bildirim sistemi
- Grafiksel analizler (chart desteği)

## Katkıda Bulunma
Bu projeye katkıda bulunmak için lütfen bir `fork` oluşturun ve değişikliklerinizi `pull request` olarak gönderin.

## Lisans
Bu proje MIT Lisansı altında sunulmaktadır.

---

**Proje Geliştiricisi**: [İsminiz]  
**İletişim**: [E-posta Adresiniz]
