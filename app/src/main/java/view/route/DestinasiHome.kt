package view.route

import com.example.roomdb.R

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    // Diperbaiki: Menggunakan R.string.data_siswa (huruf kecil)
    override val titleRes = R.string.data_siswa
}