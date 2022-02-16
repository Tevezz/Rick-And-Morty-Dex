package com.mbe.presentation.character.detail.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mbe.presentation.character.detail.model.CharacterDetailFlowState
import com.mbe.presentation.character.detail.viewmodel.CharacterDetailViewModel
import com.mbe.presentation.databinding.FragmentCharacterInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

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
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.characterFlow.collectLatest { state ->
                with(viewBinding) {
                    if (state is CharacterDetailFlowState.CharacterDetail) {
                        characterId.text = state.character.id
                        characterName.text = state.character.name
                        characterStatus.text = state.character.status
                        characterSpecies.text = state.character.species
                        characterType.text = state.character.type
                        characterGender.text = state.character.gender
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}