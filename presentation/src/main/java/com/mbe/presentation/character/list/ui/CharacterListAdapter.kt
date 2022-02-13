package com.mbe.presentation.character.list.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mbe.presentation.character.list.model.CharacterListItemModelUI

class CharacterListAdapter(
    private val onClick: (CharacterListItemModelUI) -> Unit
) :
    ListAdapter<CharacterListItemModelUI, CharacterListViewHolder>(CharacterListDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        return CharacterListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        return holder.bind(getItem(position), onClick)
    }

}