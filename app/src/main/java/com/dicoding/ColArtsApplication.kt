package com.dicoding

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

@HiltAndroidApp
class ColArtsApplication : Application()

fun <T : Any> T.toPropertyList(vararg exclude: String): List<Pair<String, Any?>> {
    return this::class.declaredMemberProperties
        .map { property ->
            property.isAccessible = true
            property.name.toTitleCase().replace("_", " ") to property.getter.call(this)
        }
        .filterNot { (name, _) ->
            exclude.any { excluded -> name.equals(excluded.toTitleCase(), ignoreCase = true) }
        }
}

fun String.toTitleCase(): String {
    return split(Regex("(?=[A-Z])|_"))
        .joinToString(" ") { word -> word.lowercase().replaceFirstChar { it.uppercase() } }
}
