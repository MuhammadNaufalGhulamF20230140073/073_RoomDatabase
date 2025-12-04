package com.example.roomdb.viewmodel.provider

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle


import com.example.roomdb.repositori.AplikasiSiswa
import com.example.roomdb.viewmodel.DetailViewModel
import com.example.roomdb.viewmodel.HomeViewModel
import com.example.roomdb.viewmodel.EntryViewModel

object PenyediaViewModel {

    val Factory = viewModelFactory {

        initializer {
            val aplikasi = this.aplikasiSiswa()
            HomeViewModel(aplikasi.container.repositoriSiswa)
        }

        initializer {
            val aplikasi = this.aplikasiSiswa()
            EntryViewModel(aplikasi.container.repositoriSiswa)
        }

        initializer {
            val aplikasi = this.aplikasiSiswa()
            val savedStateHandle: SavedStateHandle = this.createSavedStateHandle()
            DetailViewModel(savedStateHandle, aplikasi.container.repositoriSiswa)
        }
    }
}

fun CreationExtras.aplikasiSiswa(): AplikasiSiswa {
    return this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
            as? AplikasiSiswa ?: error("Application must be AplikasiSiswa")
}