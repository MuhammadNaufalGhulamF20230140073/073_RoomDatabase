package com.example.roomdb.view.route

import com.example.roomdb.R

object DestinasiEditSiswa : DestinasiNavigasi {
    override val route = "edit_siswa"
    override val titleRes = R.string.edit_siswa

    const val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{$itemIdArg}"

    fun createRoute(id: Int) = "$route/$id"
}
