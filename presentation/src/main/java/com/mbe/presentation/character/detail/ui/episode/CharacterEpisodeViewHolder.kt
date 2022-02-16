package com.mbe.presentation.character.detail.ui.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbe.presentation.character.detail.model.CharacterDetailEpisodeUI
import com.mbe.presentation.databinding.ViewHolderCharacterEpisodeBinding

class CharacterEpisodeViewHolder(
    private val parent: ViewGroup,
    private val viewBinding: ViewHolderCharacterEpisodeBinding =
        ViewHolderCharacterEpisodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
) : RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(episode: CharacterDetailEpisodeUI) {
        with(viewBinding) {
            episodeIdName.text = "#${episode.id}" + " " + "${episode.name}"
            episodeDate.text = episode.airDate
            episodeSeason.text = episode.episode
        }
    }
}