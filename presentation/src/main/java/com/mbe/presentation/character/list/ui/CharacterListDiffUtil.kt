package com.mbe.presentation.character.list.ui

import androidx.recyclerview.widget.DiffUtil
import com.mbe.presentation.character.list.model.CharacterListItemModelUI

class CharacterListDiffUtil : DiffUtil.ItemCallback<CharacterListItemModelUI>() {

    override fun areItemsTheSame(
        oldItem: CharacterListItemModelUI,
        newItem: CharacterListItemModelUI
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CharacterListItemModelUI,
        newItem: CharacterListItemModelUI
    ): Boolean {
        return oldItem.id == newItem.id
            && oldItem.name == newItem.name
            && oldItem.image == newItem.image
            && oldItem.status == newItem.status
            && oldItem.species == newItem.species
            && oldItem.location == newItem.location
    }
}