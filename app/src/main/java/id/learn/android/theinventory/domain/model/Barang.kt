package id.learn.android.theinventory.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Barang(
        val idBarang: Int,
        val jmlStok: Int,
        val kondisi: String,
        val type: String
): Parcelable