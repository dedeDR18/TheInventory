package id.learn.android.theinventory.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.lang.Exception


private val Context.dataStore by preferencesDataStore(name = "user_manager")

class UserManager(context: Context) {

    private val dataStore = context.dataStore


    val USER_ROLE = stringPreferencesKey("user_role")
    val userRole = dataStore.data.map { preferences ->
        preferences[USER_ROLE]
    }


    suspend fun saveUserRole(value:String){
        dataStore.edit { preferencesUserRole ->
            preferencesUserRole[USER_ROLE] = value
        }
    }

}
