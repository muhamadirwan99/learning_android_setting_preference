# MySettingPreference

Aplikasi Android sederhana untuk mendemonstrasikan penggunaan **SharedPreferences** dan **PreferenceFragment** dalam mengelola pengaturan/preferensi pengguna.

## 📋 Deskripsi

Aplikasi ini menunjukkan cara membuat halaman pengaturan (settings) yang dapat menyimpan data pengguna secara persisten menggunakan SharedPreferences. Data yang disimpan akan tetap ada meskipun aplikasi ditutup atau perangkat di-restart.

## ✨ Fitur

- **Penyimpanan Data Persisten**: Data pengguna disimpan otomatis menggunakan SharedPreferences
- **Input Form Beragam**: Mendukung berbagai tipe input (text, email, number, checkbox)
- **Update Real-time**: Tampilan summary berubah langsung saat data diubah
- **UI Modern**: Menggunakan Material Design dengan edge-to-edge display
- **Keyboard Khusus**: Keyboard menyesuaikan dengan tipe input (email, number)

## 🏗️ Struktur Aplikasi

### 1. MainActivity.kt
Activity utama yang bertindak sebagai container untuk menampilkan fragment pengaturan.

**Fungsi Utama:**
- Mengatur tampilan edge-to-edge untuk UI modern
- Menangani system bars (status bar & navigation bar) dengan padding
- Memuat MyPreferenceFragment ke dalam layout

### 2. MyPreferenceFragment.kt
Fragment yang mengelola logika dan tampilan halaman pengaturan.

**Komponen Penting:**
- **Key Variables**: Menyimpan identifier untuk setiap preferensi (NAME, EMAIL, AGE, PHONE, LOVE)
- **Preference Objects**: Referensi ke UI elements untuk mengupdate tampilan
- **Lifecycle Methods**:
  - `onCreatePreferences()`: Memuat layout XML dan inisialisasi data
  - `onResume()`: Register listener untuk mendeteksi perubahan data
  - `onPause()`: Unregister listener untuk mencegah memory leak
- **SharedPreferences Listener**: Mendeteksi dan merespon setiap perubahan data

### 3. preferences.xml
File XML yang mendefinisikan struktur dan tampilan halaman pengaturan.

**Elemen Preference:**
- **EditTextPreference**: Input teks untuk nama, email, umur, dan nomor telepon
- **CheckBoxPreference**: Toggle on/off untuk preferensi boolean
- **Atribut Penting**:
  - `android:key`: Identifier unik untuk menyimpan dan mengambil data
  - `android:inputType`: Menentukan tipe keyboard yang muncul
  - `android:summary`: Teks kecil yang menampilkan nilai saat ini

### 4. activity_main.xml
Layout sederhana yang berisi container untuk fragment pengaturan.

## 🔄 Alur Kerja Aplikasi

```
1. MainActivity dibuat
   ↓
2. Layout activity_main.xml dimuat
   ↓
3. MyPreferenceFragment ditambahkan ke container
   ↓
4. Fragment memuat preferences.xml
   ↓
5. Inisialisasi key dan referensi preferensi
   ↓
6. Tampilkan data tersimpan di summary (jika ada)
   ↓
7. Register listener untuk mendeteksi perubahan
   ↓
8. User mengubah data
   ↓
9. onSharedPreferenceChanged() dipanggil
   ↓
10. Update tampilan summary dengan data baru
```

## 💾 Cara Kerja SharedPreferences

SharedPreferences menyimpan data dalam format **key-value pairs** seperti ini:

```kotlin
// Menyimpan data (otomatis oleh PreferenceFragment)
SharedPreferences.Editor.putString("key_name", "John Doe")
SharedPreferences.Editor.putBoolean("key_love", true)

// Mengambil data
val name = sharedPreferences.getString("key_name", "Tidak Ada")
val isLove = sharedPreferences.getBoolean("key_love", false)
```

**Lokasi File**: Data disimpan di `/data/data/com.dicoding.mysettingpreference/shared_prefs/`

## 🎯 Konsep Penting

### 1. Mengapa Menggunakan Fragment?
Fragment memungkinkan kita untuk:
- Membuat komponen UI yang reusable
- Memisahkan logika pengaturan dari activity utama
- Mudah diintegrasikan dengan berbagai layout

### 2. Mengapa Register/Unregister Listener?
```kotlin
onResume() → register listener   // Mulai mendengarkan perubahan
onPause()  → unregister listener // Berhenti mendengarkan
```
**Alasan:**
- Mencegah **memory leak** (listener yang tidak dihapus akan tetap hidup)
- Efisiensi: Tidak perlu mendengarkan saat fragment tidak terlihat
- Best practice Android lifecycle management

### 3. Mengapa Menggunakan lateinit?
```kotlin
private lateinit var NAME: String
```
**Alasan:**
- Variabel akan diisi di method `init()`, bukan saat deklarasi
- Menghindari nullable type (`String?`) yang merepotkan
- Kotlin akan error jika diakses sebelum diinisialisasi (fail-fast)

### 4. Mengapa Update Summary?
Summary menampilkan nilai saat ini di bawah judul preferensi, sehingga user bisa melihat data yang tersimpan tanpa perlu membuka dialog edit.

## 🛠️ Teknologi yang Digunakan

- **Language**: Kotlin
- **Min SDK**: Android 5.0 (API 21)
- **Architecture**: Fragment-based
- **Storage**: SharedPreferences
- **UI Components**:
  - PreferenceFragmentCompat
  - EditTextPreference
  - CheckBoxPreference

## 📱 Cara Menggunakan

1. **Clone atau Download** project ini
2. **Buka dengan Android Studio**
3. **Sync Gradle** dan tunggu dependencies selesai di-download
4. **Run aplikasi** di emulator atau device fisik
5. **Isi form pengaturan** dan data akan otomatis tersimpan
6. **Tutup dan buka kembali** aplikasi untuk memverifikasi data tetap tersimpan

## 🔍 Testing

Untuk memverifikasi SharedPreferences bekerja:

1. Isi semua form pengaturan
2. Tutup aplikasi (swipe dari recent apps)
3. Buka kembali aplikasi
4. Data yang diisi sebelumnya akan tetap ada

## 📚 Referensi Belajar

- [Android Preferences Documentation](https://developer.android.com/guide/topics/ui/settings)
- [SharedPreferences Guide](https://developer.android.com/training/data-storage/shared-preferences)
- [Fragment Lifecycle](https://developer.android.com/guide/fragments/lifecycle)

## 👨‍💻 Pengembang

Project ini dibuat untuk tujuan pembelajaran pengembangan aplikasi Android dengan fokus pada data persistence menggunakan SharedPreferences.

## 📄 Lisensi

Project ini bebas digunakan untuk keperluan belajar dan pengembangan.

---

**Happy Coding! 🚀**

