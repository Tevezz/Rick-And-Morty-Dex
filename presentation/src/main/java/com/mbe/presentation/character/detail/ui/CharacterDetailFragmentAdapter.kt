package com.mbe.presentation.character.detail.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mbe.presentation.character.detail.ui.episode.CharacterEpisodeFragment
import com.mbe.presentation.character.detail.ui.info.CharacterInfoFragment
import com.mbe.presentation.character.detail.ui.location.CharacterLocationFragment

class CharacterDetailFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CharacterInfoFragment()
            1 -> CharacterLocationFragment()
            else -> CharacterEpisodeFragment()
        }
    }
}