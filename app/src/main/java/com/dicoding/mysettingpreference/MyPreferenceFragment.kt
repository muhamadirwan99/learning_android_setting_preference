package com.dicoding.mysettingpreference

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat

// Fragment khusus untuk mengelola halaman pengaturan/preferensi pengguna
// Implements OnSharedPreferenceChangeListener agar bisa mendeteksi perubahan data preferensi secara real-time
class MyPreferenceFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    // Variabel untuk menyimpan key/kunci dari setiap preferensi
    // Key ini digunakan untuk mengidentifikasi data mana yang berubah di SharedPreferences
    private lateinit var NAME: String
    private lateinit var EMAIL: String
    private lateinit var AGE: String
    private lateinit var PHONE: String
    private lateinit var LOVE: String

    // Variabel untuk menyimpan referensi ke objek preferensi di UI
    // Diperlukan agar kita bisa mengupdate tampilan (summary) saat data berubah
    private lateinit var namePreference: EditTextPreference
    private lateinit var emailPreference: EditTextPreference
    private lateinit var agePreference: EditTextPreference
    private lateinit var phonePreference: EditTextPreference
    private lateinit var isLoveMuPreference: CheckBoxPreference

    companion object {
        // Nilai default yang akan ditampilkan jika user belum mengisi data
        private const val DEFAULT_VALUE = "Tidak Ada"
    }

    // Method ini dipanggil saat fragment pertama kali dibuat
    // Di sini kita setup tampilan preferensi dari file XML
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Memuat layout preferensi dari file XML ke dalam fragment
        addPreferencesFromResource(R.xml.preferences)
        // Inisialisasi key dan referensi preferensi
        init()
        // Set tampilan awal summary berdasarkan data yang tersimpan
        setSummaries()
    }

    // Method untuk inisialisasi semua key dan referensi preferensi
    // Diperlukan agar kita punya akses ke data dan UI element yang akan diupdate
    private fun init() {
        // Ambil key dari string resources agar mudah di-maintain dan mendukung multilanguage
        NAME = resources.getString(R.string.key_name)
        EMAIL = resources.getString(R.string.key_email)
        AGE = resources.getString(R.string.key_age)
        PHONE = resources.getString(R.string.key_phone)
        LOVE = resources.getString(R.string.key_love)

        // Ambil referensi ke setiap preferensi yang ada di XML berdasarkan key-nya
        // Referensi ini dibutuhkan untuk mengupdate tampilan summary secara dinamis
        namePreference = findPreference<EditTextPreference>(NAME) as EditTextPreference
        emailPreference = findPreference<EditTextPreference>(EMAIL) as EditTextPreference
        agePreference = findPreference<EditTextPreference>(AGE) as EditTextPreference
        phonePreference = findPreference<EditTextPreference>(PHONE) as EditTextPreference
        isLoveMuPreference = findPreference<CheckBoxPreference>(LOVE) as CheckBoxPreference
    }

    // Method untuk menampilkan data yang sudah tersimpan ke dalam summary setiap preferensi
    // Summary adalah teks kecil di bawah judul preferensi yang menampilkan nilai saat ini
    private fun setSummaries() {
        // Ambil SharedPreferences yang berisi semua data preferensi yang tersimpan
        val sh = preferenceManager.sharedPreferences
        // Set summary dari data yang tersimpan, atau tampilkan DEFAULT_VALUE jika belum ada data
        namePreference.summary = sh?.getString(NAME, DEFAULT_VALUE)
        emailPreference.summary = sh?.getString(EMAIL, DEFAULT_VALUE)
        agePreference.summary = sh?.getString(AGE, DEFAULT_VALUE)
        phonePreference.summary = sh?.getString(PHONE, DEFAULT_VALUE)
        // Untuk checkbox, set status checked berdasarkan data tersimpan
        isLoveMuPreference.isChecked = sh?.getBoolean(LOVE, false) ?: false
    }

    // Method ini dipanggil saat fragment terlihat dan aktif di layar
    // Kita register listener di sini agar mulai mendengarkan perubahan data
    override fun onResume() {
        super.onResume()
        // Daftarkan listener agar method onSharedPreferenceChanged dipanggil saat ada perubahan
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    // Method ini dipanggil saat fragment tidak lagi terlihat (minimize/pindah activity)
    // Kita unregister listener untuk menghindari memory leak
    override fun onPause() {
        super.onPause()
        // Hapus listener agar tidak terus mendengarkan saat fragment tidak aktif
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    // Method ini otomatis dipanggil setiap kali ada perubahan di SharedPreferences
    // Kita update tampilan summary sesuai preferensi yang berubah
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        // Cek preferensi mana yang berubah berdasarkan key-nya
        // Kita hanya update UI untuk preferensi yang berubah, bukan semuanya (efisiensi)
        if (key == NAME){
            // Update summary nama dengan nilai terbaru dari SharedPreferences
            namePreference.summary = sharedPreferences?.getString(NAME, DEFAULT_VALUE)
        }

        if (key == EMAIL){
            // Update summary email dengan nilai terbaru
            emailPreference.summary = sharedPreferences?.getString(EMAIL, DEFAULT_VALUE)
        }

        if (key == AGE){
            // Update summary umur dengan nilai terbaru
            agePreference.summary = sharedPreferences?.getString(AGE, DEFAULT_VALUE)
        }

        if (key == PHONE){
            // Update summary nomor telepon dengan nilai terbaru
            phonePreference.summary = sharedPreferences?.getString(PHONE, DEFAULT_VALUE)
        }

        if (key == LOVE){
            // Update status checkbox dengan nilai boolean terbaru
            isLoveMuPreference.isChecked = sharedPreferences?.getBoolean(LOVE, false) ?: false
        }
    }
}