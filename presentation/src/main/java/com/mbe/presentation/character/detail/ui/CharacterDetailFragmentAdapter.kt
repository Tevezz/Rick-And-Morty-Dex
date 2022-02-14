package com.mbe.presentation.character.detail.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CharacterDetailFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CharacterInfoFragment()
            1 -> CharacterLocationFragment()
            else -> Fragment()
        }
    }
}