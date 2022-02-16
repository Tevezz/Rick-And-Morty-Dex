package com.mbe.presentation.character.list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mbe.presentation.character.list.model.CharacterListItemModelUI
import com.mbe.presentation.databinding.ViewHolderCharacterListBinding

class CharacterListViewHolder(
    private val parent: ViewGroup,
    private val viewBinding: ViewHolderCharacterListBinding =
        ViewHolderCharacterListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
) : RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(character: CharacterListItemModelUI, onClick: (CharacterListItemModelUI) -> Unit) {
        with(viewBinding) {
            characterImage.load(character.image)
            characterId.text = character.id
            characterName.text = character.name
            characterStatus.text = character.status
            characterSpecies.text = character.species
            root.setOnClickListener { onClick(character) }
        }
    }
}