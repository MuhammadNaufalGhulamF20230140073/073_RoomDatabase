package com.example.roomdb.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.repositori.RepositoriSiswa
import com.example.roomdb.view.route.DestinasiEditSiswa
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel() {

    //❗ State form
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    //❗ State loading
    var isLoading by mutableStateOf(true)
        private set

    //❗ Ambil ID dari argumen route
    private val idSiswa: Int =
        savedStateHandle[DestinasiEditSiswa.itemIdArg]
            ?: -1

    /**
     * Dipanggil dari EditSiswaScreen via LaunchedEffect
     */
    fun loadSiswa() {
        if (idSiswa == -1) {
            Log.e("EditViewModel", "ID siswa tidak ditemukan!")
            isLoading = false
            return
        }

        viewModelScope.launch {
            try {
                val siswa = repositoriSiswa.getSiswaStream(idSiswa)
                    .filterNotNull()
                    .first()

                uiStateSiswa = siswa.toUiStateSiswa(isEntryValid = true)
                Log.d("EditViewModel", "Load sukses, ID: $idSiswa")

            } catch (e: Exception) {
                Log.e("EditViewModel", "Gagal load siswa: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa)
        )
    }

    private fun validasiInput(detail: DetailSiswa): Boolean {
        return detail.nama.isNotBlank() &&
                detail.alamat.isNotBlank() &&
                detail.telpon.isNotBlank()
    }

    suspend fun updateSiswa() {
        if (!uiStateSiswa.isEntryValid) {
            Log.e("EditViewModel", "Validasi gagal, data kosong!")
            return
        }

        try {
            repositoriSiswa.updateSiswa(uiStateSiswa.detailSiswa.toSiswa())
            Log.d("EditViewModel", "Update sukses")
        } catch (e: Exception) {
            Log.e("EditViewModel", "Gagal update siswa: ${e.message}")
        }
    }
}
