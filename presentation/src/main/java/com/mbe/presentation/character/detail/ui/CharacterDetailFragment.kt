package com.mbe.presentation.character.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import com.mbe.presentation.R
import com.mbe.presentation.character.detail.viewmodel.CharacterDetailViewModel
import com.mbe.presentation.databinding.FragmentCharacterDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

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
    }

    private fun initViews() {
        with(viewBinding) {
            characterDetailsImage.load(viewModel.character.image)
            characterDetailsViewPager.adapter = CharacterDetailFragmentAdapter(this@CharacterDetailFragment)
            TabLayoutMediator(characterDetailsTabs, characterDetailsViewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> getString(R.string.character_info)
                    1 -> getString(R.string.character_location)
                    else -> getString(R.string.character_episodes)
                }
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}