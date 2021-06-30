package id.learn.android.theinventory.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Barang(
        val idBarang: Int,
        val namaBarang:String,
        val stokBarang: Int,
        val merkBarang: String
): Parcelable