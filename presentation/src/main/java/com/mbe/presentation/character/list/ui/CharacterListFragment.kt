package com.mbe.presentation.character.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mbe.presentation.R
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
                with(viewBinding) {
                    when (state) {
                        is CharacterListFlowState.CharacterList -> {
                            characterListPageNum.text = getString(
                                R.string.character_list_page_format,
                                state.characters.currentPage.toString(),
                                state.characters.pages.toString()
                            )
                            characterList.getTypedAdapter<CharacterListAdapter>()
                                ?.submitList(state.characters.list)
                            characterListPrevBtn.visibility = if (state.characters.hasPrev) View.VISIBLE else View.GONE
                            characterListNextBtn.visibility = if (state.characters.hasNext) View.VISIBLE else View.GONE
                            characterListLoader.visibility = View.GONE
                        }
                        is CharacterListFlowState.Loading -> {
                            characterListLoader.visibility = View.VISIBLE
                        }
                        is CharacterListFlowState.Error -> {
                            Toast.makeText(context, getString(R.string.character_list_error), Toast.LENGTH_SHORT).show()
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