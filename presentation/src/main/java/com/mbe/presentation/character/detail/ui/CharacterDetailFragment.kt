package com.mbe.presentation.character.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import com.mbe.presentation.R
import com.mbe.presentation.character.detail.model.CharacterDetailFlowState
import com.mbe.presentation.character.detail.viewmodel.CharacterDetailViewModel
import com.mbe.presentation.databinding.FragmentCharacterDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val viewBinding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        with(viewBinding) {
            characterDetailsViewPager.adapter = CharacterDetailFragmentAdapter(this@CharacterDetailFragment)
            TabLayoutMediator(characterDetailsTabs, characterDetailsViewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> getString(R.string.character_detail_info)
                    1 -> getString(R.string.character_detail_location)
                    else -> getString(R.string.character_detail_episodes)
                }
            }.attach()
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.characterFlow.collectLatest { state ->
                with(viewBinding) {
                    when (state) {
                        is CharacterDetailFlowState.CharacterDetail -> {
                            characterDetailsImage.load(state.character.image)
                            characterDetailsLoader.visibility = View.GONE
                        }
                        is CharacterDetailFlowState.Loading -> {
                            characterDetailsLoader.visibility = View.VISIBLE
                        }
                        is CharacterDetailFlowState.Error -> {
                            Toast.makeText(context, getString(R.string.character_detail_error), Toast.LENGTH_SHORT).show()
                        }
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