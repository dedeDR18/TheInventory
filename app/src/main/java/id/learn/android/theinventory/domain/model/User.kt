package id.learn.android.theinventory.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val nama: String,
    val nim: Int,
    val kelas: String,
    val noHp: String,
    val username: String,
    var isNew: Boolean = false,
    val role: String
) : Parcelable
