package com.mbe.presentation.character.list.ui

import androidx.recyclerview.widget.DiffUtil
import com.mbe.presentation.character.list.model.CharacterListModelUI

class CharacterListDiffUtil : DiffUtil.ItemCallback<CharacterListModelUI>() {

    override fun areItemsTheSame(
        oldItem: CharacterListModelUI,
        newItem: CharacterListModelUI
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CharacterListModelUI,
        newItem: CharacterListModelUI
    ): Boolean {
        return oldItem.id == newItem.id
            && oldItem.name == newItem.name
            && oldItem.status == newItem.status
            && oldItem.species == newItem.species
    }
}