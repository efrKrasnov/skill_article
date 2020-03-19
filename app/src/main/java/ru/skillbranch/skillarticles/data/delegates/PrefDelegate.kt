package ru.skillbranch.skillarticles.data.delegates

import ru.skillbranch.skillarticles.data.local.PrefManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefDelegate<T: Any>(private val defaultValue: T) :
    ReadWriteProperty<PrefManager, T?> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: PrefManager, property: KProperty<*>): T? {
        val preferences = thisRef.preferences
        val key = defaultValue.toString()
        with(preferences)  {
            return when(defaultValue) {
                is Boolean -> getBoolean(key, false) as T
                is String -> getString(key, "") as T
                is Float -> getFloat(key, 0.0f) as T
                is Int-> getInt(key, 0) as T
                is Long -> getLong(key, 0) as T
                else -> error("This type cannot be got from preferences")
            }
        }
    }

    override fun setValue(thisRef: PrefManager, property: KProperty<*>, value: T?) {
        val editor = thisRef.preferences.edit()
        val key = value!!.toString()
        with(editor)  {
            when(value) {
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
                is Float -> putFloat(key, value)
                is Int-> putInt(key, value)
                is Long -> putLong(key, value)
                else -> error("This type cannot be stored into preferences")
            }
        }
    }

}