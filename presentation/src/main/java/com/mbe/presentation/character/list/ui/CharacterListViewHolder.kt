package com.mbe.presentation.character.list.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mbe.presentation.R
import com.mbe.presentation.character.list.model.CharacterListItemModelUI
import com.mbe.presentation.character.list.model.CharacterListStatus
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
            characterListId.text = root.context.getString(R.string.character_list_id_prefix, character.id)
            characterListName.text = character.name
            characterListStatusImg.imageTintList = getStatusColor(character.status)
            characterListStatusSpecies.text = root.context.getString(
                R.string.character_list_status_species_format, character.status.name, character.species
            )
            characterListLocation.text = character.location
            root.setOnClickListener { onClick(character) }
        }
    }
    private fun getStatusColor(status: CharacterListStatus): ColorStateList {
        return ColorStateList.valueOf(
            when (status) {
                CharacterListStatus.Alive -> Color.GREEN
                CharacterListStatus.Dead -> Color.RED
                CharacterListStatus.Unknown -> Color.GRAY
            }
        )
    }
}