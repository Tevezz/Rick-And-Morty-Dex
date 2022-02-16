package com.mbe.presentation.character.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mbe.presentation.character.list.model.CharacterListFlowState
import com.mbe.presentation.character.list.viewmodel.CharacterListViewModel
import com.mbe.presentation.databinding.FragmentCharacterListBinding
import com.mbe.presentation.extension.getTypedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val viewBinding get() = _binding!!

    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        with(viewBinding) {
            characterList.adapter = CharacterListAdapter {
//                findNavController().navigate(viewModel.getNavigationAction(it.id))
            }
            characterListNextBtn.setOnClickListener {
                viewModel.requestNextPage()
            }
            characterListPrevBtn.setOnClickListener {
                viewModel.requestPreviousPage()
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.characterListFlow.collectLatest { state ->
                when (state) {
                    is CharacterListFlowState.CharacterList -> {
                        with(viewBinding) {
                            characterListPageNum.text = "${state.characters.currentPage} / ${state.characters.pages}" // TODO Add resource string
                            characterList.getTypedAdapter<CharacterListAdapter>()?.submitList(state.characters.list)
                        }
                    }
                    is CharacterListFlowState.Loading -> {
                        // TODO
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