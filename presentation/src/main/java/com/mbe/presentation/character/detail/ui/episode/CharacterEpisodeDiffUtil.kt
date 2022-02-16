package com.mbe.presentation.character.detail.ui.episode

import androidx.recyclerview.widget.DiffUtil
import com.mbe.presentation.character.detail.model.CharacterDetailEpisodeUI

class CharacterEpisodeDiffUtil : DiffUtil.ItemCallback<CharacterDetailEpisodeUI>() {

    override fun areItemsTheSame(
        oldItem: CharacterDetailEpisodeUI,
        newItem: CharacterDetailEpisodeUI
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CharacterDetailEpisodeUI,
        newItem: CharacterDetailEpisodeUI
    ): Boolean {
        return oldItem.id == newItem.id
            && oldItem.name == newItem.name
            && oldItem.airDate == newItem.airDate
            && oldItem.episode == newItem.episode
    }
}