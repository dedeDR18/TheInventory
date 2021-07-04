package id.learn.android.theinventory.domain.model

data class Peminjaman(
    val idPeminjaman:Int,
    val idMahasiswaPeminjam:String,
    val barang:String,
    val tanggalPeminjaman:String,
    val tanggalPengembalian:String,
    val status:String
)