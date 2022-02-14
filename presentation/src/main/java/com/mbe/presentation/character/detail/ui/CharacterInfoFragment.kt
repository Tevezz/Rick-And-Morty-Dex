package com.mbe.presentation.character.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mbe.presentation.character.detail.viewmodel.CharacterDetailViewModel
import com.mbe.presentation.databinding.FragmentCharacterInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {

    private var _binding: FragmentCharacterInfoBinding? = null
    private val viewBinding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by viewModels({ requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterInfoBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            characterId.text = viewModel.character.id.toString()
            characterName.text = viewModel.character.name
            characterStatus.text = viewModel.character.status
            characterSpecies.text = viewModel.character.species
            characterType.text = viewModel.character.type
            characterGender.text = viewModel.character.gender
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}