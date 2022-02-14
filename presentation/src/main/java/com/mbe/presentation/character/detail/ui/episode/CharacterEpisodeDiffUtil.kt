package com.mbe.presentation.character.detail.ui.episode

import androidx.recyclerview.widget.DiffUtil
import com.mbe.presentation.character.detail.model.CharacterEpisodeModelUI

class CharacterEpisodeDiffUtil : DiffUtil.ItemCallback<CharacterEpisodeModelUI>() {

    override fun areItemsTheSame(
        oldItem: CharacterEpisodeModelUI,
        newItem: CharacterEpisodeModelUI
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CharacterEpisodeModelUI,
        newItem: CharacterEpisodeModelUI
    ): Boolean {
        return oldItem.id == newItem.id
            && oldItem.name == newItem.name
            && oldItem.airDate == newItem.airDate
            && oldItem.episode == newItem.episode
    }
}