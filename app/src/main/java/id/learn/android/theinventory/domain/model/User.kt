package id.learn.android.theinventory.domain.model

import android.os.Parcelable
import android.provider.ContactsContract
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val nama: String,
    val nim: Long,
    val kelas: String,
    val noHp: String,
    val role: String,
    val email: String
) : Parcelable
