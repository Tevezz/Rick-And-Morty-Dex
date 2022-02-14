package com.mbe.domain.location.exception

import java.lang.Exception

class InvalidLocationUrlException : Exception() {
    override val message: String
        get() = "Invalid Location URL"
}