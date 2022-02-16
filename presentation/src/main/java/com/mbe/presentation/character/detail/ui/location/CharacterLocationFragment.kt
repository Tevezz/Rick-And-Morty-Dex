package com.mbe.presentation.character.detail.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mbe.presentation.character.detail.model.CharacterDetailFlowState
import com.mbe.presentation.character.detail.viewmodel.CharacterDetailViewModel
import com.mbe.presentation.databinding.FragmentCharacterLocationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharacterLocationFragment : Fragment() {

    private var _binding: FragmentCharacterLocationBinding? = null
    private val viewBinding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by viewModels({ requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterLocationBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.characterFlow.collectLatest { state ->
                if (state is CharacterDetailFlowState.CharacterDetail) {
                    viewBinding.characterOriginName.text = state.character.origin.name
                    viewBinding.characterOriginType.text = state.character.origin.type
                    viewBinding.characterOriginDimension.text = state.character.origin.dimension
                    viewBinding.characterLocationName.text = state.character.location.name
                    viewBinding.characterLocationType.text = state.character.location.type
                    viewBinding.characterLocationDimension.text = state.character.location.dimension
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}