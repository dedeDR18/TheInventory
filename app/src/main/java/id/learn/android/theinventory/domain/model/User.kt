package id.learn.android.theinventory.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id:Int,
    val nama:String,
    val nim:Int,
    val kelas: String,
    val noHp: String,
    val username:String,
) : Parcelable
