package ru.skillbranch.skillarticles.data.delegates

import ru.skillbranch.skillarticles.data.local.PrefManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefDelegate<T: Any>(private val defaultValue: T) {

    private var storedValue: T? = null

    operator fun provideDelegate(
        thisRef: PrefManager,
        prop: KProperty<*>
    ) : ReadWriteProperty<PrefManager, T?>  {
        val key = prop.name

        return object : ReadWriteProperty<PrefManager, T?>  {

            override fun getValue(thisRef: PrefManager, property: KProperty<*>): T? {
                if(storedValue == null) {
                    val preferences = thisRef.preferences
                    @Suppress("UNCHECKED_CAST")
                    storedValue = with (preferences)  {
                        when(defaultValue) {
                            is Boolean -> getBoolean(key, defaultValue as Boolean) as T
                            is String -> getString(key, defaultValue as String) as T
                            is Float -> getFloat(key, defaultValue as Float) as T
                            is Int-> getInt(key, defaultValue as Int) as T
                            is Long -> getLong(key, defaultValue as Long) as T
                            else -> error("This type cannot be got from preferences")
                        }
                    }
                }
                return storedValue
            }

            override fun setValue(thisRef: PrefManager, property: KProperty<*>, value: T?) {
                val editor = thisRef.preferences.edit()
                with(editor)  {
                    when(value) {
                        is Boolean -> putBoolean(key, value)
                        is String -> putString(key, value)
                        is Float -> putFloat(key, value)
                        is Int-> putInt(key, value)
                        is Long -> putLong(key, value)
                        else -> error("This type cannot be stored into preferences")
                    }
                    apply()
                }
                storedValue = value
            }
        }

    }


}