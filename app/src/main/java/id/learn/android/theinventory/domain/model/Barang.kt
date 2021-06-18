package id.learn.android.theinventory.domain.model

data class Barang(
        val idBarang: Int,
        val jmlStok: Int,
        val kondisi: String,
        val type: String
)