package com.mbe.domain.character.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val url: String,
    val created: String,
    val originUrl: String,
    val locationUrl: String,
    val episode: List<String>
) : Parcelable