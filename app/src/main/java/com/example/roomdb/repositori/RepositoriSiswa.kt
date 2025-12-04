package com.example.roomdb.repositori

import com.example.roomdb.room.Siswa
import com.example.roomdb.room.SiswaDao
import kotlinx.coroutines.flow.Flow

interface RepositoriSiswa {

    fun getAllSiswaStream(): Flow<List<Siswa>>

    fun getSiswaStream(id: Int): Flow<Siswa?>

    suspend fun insertSiswa(siswa: Siswa)

    suspend fun updateSiswa(siswa: Siswa)

    suspend fun deleteSiswa(siswa: Siswa)
}

class OfflineRepositoriSiswa(
    private val siswaDao: SiswaDao
) : RepositoriSiswa {

    override fun getAllSiswaStream(): Flow<List<Siswa>> =
        siswaDao.getAllSiswa()

    override fun getSiswaStream(id: Int): Flow<Siswa?> =
        siswaDao.getSiswa(id)

    override suspend fun insertSiswa(siswa: Siswa) =
        siswaDao.insert(siswa)

    override suspend fun updateSiswa(siswa: Siswa) =
        siswaDao.update(siswa)

    override suspend fun deleteSiswa(siswa: Siswa) =
        siswaDao.delete(siswa)
}