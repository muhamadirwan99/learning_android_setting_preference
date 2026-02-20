package com.dicoding.mysettingpreference

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Activity utama yang menampilkan halaman pengaturan
class MainActivity : AppCompatActivity() {
    // Method yang dipanggil saat activity pertama kali dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Aktifkan tampilan edge-to-edge agar konten menyentuh tepi layar (modern UI)
        enableEdgeToEdge()
        // Set layout XML sebagai tampilan utama activity
        setContentView(R.layout.activity_main)

        // Setup padding agar konten tidak tertutup system bars (status bar & navigation bar)
        // Listener ini mendeteksi ukuran system bars dan mengatur padding secara otomatis
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.setting_holder)) { v, insets ->
            // Ambil ukuran system bars (status bar di atas & navigation bar di bawah)
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Set padding agar konten tidak tertimpa system bars
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            // Return insets agar bisa diproses oleh view lainnya jika diperlukan
            insets
        }

        // Tambahkan fragment preferensi ke dalam container setting_holder
        // Fragment akan mengisi seluruh area container dan menampilkan halaman pengaturan
        supportFragmentManager.beginTransaction().add(R.id.setting_holder, MyPreferenceFragment()).commit()
    }
}