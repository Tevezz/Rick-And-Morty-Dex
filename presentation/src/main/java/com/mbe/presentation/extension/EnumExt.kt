package com.mbe.presentation.extension

internal inline fun <reified T : Enum<T>> safeValueOf(type: String, default: T): T {
    return try {
        java.lang.Enum.valueOf(T::class.java, type)
    } catch (e: Exception) {
        default
    }
}