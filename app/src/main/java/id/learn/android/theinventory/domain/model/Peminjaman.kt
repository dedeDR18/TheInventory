package id.learn.android.theinventory.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Peminjaman(
    val idPeminjaman:String,
    val idMahasiswaPeminjam:Long,
    val namaPeminjam:String,
    val barang:String,
    val tanggalPeminjaman:String,
    val tanggalPengembalian:String,
    val status:String
):Parcelable