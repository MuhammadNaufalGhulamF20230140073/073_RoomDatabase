package com.example.roomdb

import android.app.Application
// Import container dari package repositori
import com.example.roomdb.repositori.ContainerApp
import com.example.roomdb.repositori.ContainerDataApp

class AplikasiSiswa : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        // Panggil ContainerDataApp yang sekarang berada di package repositori
        container = ContainerDataApp(this)
    }
}