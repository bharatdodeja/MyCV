package com.bharatdodeja.mycv.framework.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONException
import org.json.JSONObject

abstract class GenericModel {

    fun toJSONObject(): JSONObject? {
        var jsonObj: JSONObject? = null
        try {
            jsonObj = JSONObject(this.toString())
        } catch (ex: JSONException) {
            ex.printStackTrace()
        }

        return jsonObj
    }

    override fun toString(): String {
        val gson = GsonBuilder().serializeNulls().create()
        return gson.toJson(this)
    }

    companion object {

        fun <T> newInstance(rawString: String, type: Class<T>): T? {
            val gson = Gson()

            return try {
                gson.fromJson(rawString, type)
            } catch (ex: Exception) {
                ex.printStackTrace()
                null
            }

        }
    }

}