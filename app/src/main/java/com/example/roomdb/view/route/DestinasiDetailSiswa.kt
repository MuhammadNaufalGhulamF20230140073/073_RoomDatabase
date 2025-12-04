package com.example.roomdb.view.route

import com.example.roomdb.R
import com.example.roomdb.view.route.DestinasiNavigasi

object DestinasiDetailSiswa {
    const val route = "detail_siswa"
    const val itemIdArg = "itemId"

    val routeWithArgs = "$route/{$itemIdArg}"

    val titleRes = R.string.detail_siswa

    fun createRoute(itemId: Int): String = "$route/$itemId"
}