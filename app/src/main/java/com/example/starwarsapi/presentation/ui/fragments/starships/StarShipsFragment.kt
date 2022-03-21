package com.example.starwarsapi.presentation.ui.fragments.starships

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapi.databinding.FragmentStarShipsBinding
import com.example.starwarsapi.domain.common.base.BaseFragment
import com.example.starwarsapi.domain.starships.entity.StarShipsEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class StarShipsFragment : BaseFragment<FragmentStarShipsBinding>() {

    private val viewModel: StarShipsViewModel by viewModels()
    private val adapter: StarShipsAdapter by lazy {
        StarShipsAdapter()
    }

    override fun setupObservers() {
        observeState()
        observeStarShips()
    }

    private fun observeStarShips() {
        viewModel.starShipsList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleStarShips(it) }.launchIn(lifecycleScope)
    }

    private fun handleStarShips(ships: List<StarShipsEntity>) {
        adapter.submitList(ships)
        Log.e("TAG", "handleStarShips:$ships ")
    }

    private fun observeState() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }.launchIn(lifecycleScope)
    }

    private fun handleState(state: StarShipsViewModel.StarShipsFragmentState) {
        when (state) {
            is StarShipsViewModel.StarShipsFragmentState.IsLoading -> handleLoading(state.isLoading)
            is StarShipsViewModel.StarShipsFragmentState.ShowToast -> Toast.makeText(
                requireContext(),
                state.message,
                Toast.LENGTH_SHORT
            ).show()
            is StarShipsViewModel.StarShipsFragmentState.Init -> Unit
        }
    }

    private fun handleLoading(loading: Boolean) {
        binding.progress.isVisible = loading
    }

    override fun setupUI() {
        binding.rvStarShips.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@StarShipsFragment.adapter
        }
    }

    override fun bind(): FragmentStarShipsBinding {
        return FragmentStarShipsBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {

    }

}