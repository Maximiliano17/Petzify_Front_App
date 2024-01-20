package com.maxi.petzify.data.core.storage

import android.content.Context
import javax.inject.Inject

class Pref @Inject constructor(val context:Context){
    val SHARED_NAME = "MyDataBase"
    val storage = context.getSharedPreferences(SHARED_NAME,0)

    fun saveData(name: String, data: Any) {
        when (data) {
            is String -> storage.edit().putString(name, data).apply()
            is Boolean -> storage.edit().putBoolean(name, data).apply()
        }
    }

    fun getSring(name:String) = storage.getString(name, "").toString()

    fun getBoolean(name:String) = storage.getBoolean(name, false)


}