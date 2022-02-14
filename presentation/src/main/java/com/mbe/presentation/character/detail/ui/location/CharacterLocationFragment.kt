package com.mbe.presentation.character.detail.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mbe.presentation.character.detail.model.LocationFlowState
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
        viewModel.getLocation()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.locationStateFlow.collectLatest { state ->
                when (state) {
                    is LocationFlowState.Location -> {
                        state.data.also { (origin, location) ->
                            viewBinding.characterOriginName.text = origin.name
                            viewBinding.characterOriginType.text = origin.type
                            viewBinding.characterOriginDimension.text = origin.dimension
                            viewBinding.characterLocationName.text = location.name
                            viewBinding.characterLocationType.text = location.type
                            viewBinding.characterLocationDimension.text = location.dimension
                        }
                        // TODO Hide Loading
                    }
                    is LocationFlowState.Loading -> {}
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}