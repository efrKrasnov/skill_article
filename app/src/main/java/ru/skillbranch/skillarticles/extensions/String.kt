package ru.skillbranch.skillarticles.extensions

import android.content.res.Resources
import androidx.core.os.ConfigurationCompat

fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> {
    val pairList = mutableListOf<Int>()
    if (substr.isNotEmpty()) {


        val tempSearch = if (ignoreCase) this!!.toLowerCase(
            ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0)
        ) else this
        val tempSubstr = if (ignoreCase) substr.toLowerCase(
            ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0)
        ) else substr

        val regex = tempSubstr.toRegex()

        var match = regex.find(tempSearch.toString())
        while (match != null) {
            pairList.add(match.range.first)
            match = match.next()
        }
    }
    return pairList
}