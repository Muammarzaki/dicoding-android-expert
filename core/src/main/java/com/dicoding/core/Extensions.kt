package com.dicoding.core

import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible


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
