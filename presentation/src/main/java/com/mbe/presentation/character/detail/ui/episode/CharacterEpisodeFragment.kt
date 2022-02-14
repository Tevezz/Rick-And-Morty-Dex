package com.mbe.presentation.character.detail.ui.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mbe.presentation.character.detail.model.EpisodeFlowState
import com.mbe.presentation.character.detail.viewmodel.CharacterDetailViewModel
import com.mbe.presentation.databinding.FragmentCharacterEpisodeBinding
import com.mbe.presentation.extension.getTypedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharacterEpisodeFragment : Fragment() {

    private var _binding: FragmentCharacterEpisodeBinding? = null
    private val viewBinding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by viewModels({ requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterEpisodeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEpisodes()
        initViews()
        initObservers()
    }

    private fun initViews() {
        with(viewBinding) {
            characterEpisodesList.adapter = CharacterEpisodeAdapter()
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.episodeStateFlow.collectLatest { state ->
                when (state) {
                    is EpisodeFlowState.Episodes -> {
                        viewBinding.characterEpisodesList.
                        getTypedAdapter<CharacterEpisodeAdapter>()?.submitList(state.episodes)
                    }
                    is EpisodeFlowState.Loading -> {

                    }
                    is EpisodeFlowState.Error -> {

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