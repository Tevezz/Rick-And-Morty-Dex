package com.mbe.presentation.character.detail.ui.episode

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mbe.presentation.character.detail.model.CharacterEpisodeModelUI

class CharacterEpisodeAdapter :
    ListAdapter<CharacterEpisodeModelUI, CharacterEpisodeViewHolder>(CharacterEpisodeDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterEpisodeViewHolder {
        return CharacterEpisodeViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CharacterEpisodeViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}