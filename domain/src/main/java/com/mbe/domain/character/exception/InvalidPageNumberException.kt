package com.mbe.domain.character.exception

class InvalidPageNumberException : Exception() {
    override val message: String
        get() = "Invalid page number"
}