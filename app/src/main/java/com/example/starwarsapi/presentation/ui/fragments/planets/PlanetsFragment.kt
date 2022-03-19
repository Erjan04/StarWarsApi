package com.example.starwarsapi.presentation.ui.fragments.planets

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapi.databinding.FragmentPlanetsBinding
import com.example.starwarsapi.domain.common.base.BaseFragment
import com.example.starwarsapi.domain.planets.entity.PlanetsEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PlanetsFragment : BaseFragment<FragmentPlanetsBinding>() {

    private val viewModel: PlanetsViewModel by viewModels()
    private val adapter: PlanetsAdapter by lazy {
        PlanetsAdapter()
    }

    override fun setupObservers() {
        observeState()
        observePlanets()
    }

    private fun observePlanets() {
        viewModel.planetsList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handlePlanets(it) }.launchIn(lifecycleScope)
    }

    private fun handlePlanets(planets: List<PlanetsEntity>) {
        adapter.submitList(planets)
    }

    private fun observeState() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }.launchIn(lifecycleScope)
    }

    private fun handleState(state: PlanetsViewModel.PlanetsFragmentState) {
        when (state) {
            is PlanetsViewModel.PlanetsFragmentState.IsLoading -> handleLoading(state.isLoading)
            is PlanetsViewModel.PlanetsFragmentState.ShowToast -> Toast.makeText(
                requireContext(),
                state.message,
                Toast.LENGTH_SHORT
            ).show()
            is PlanetsViewModel.PlanetsFragmentState.Init -> Unit
        }
    }

    private fun handleLoading(loading: Boolean) {
        binding.progress.isVisible = loading
    }

    override fun setupUI() {
        binding.rvPlanets.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PlanetsFragment.adapter
        }
    }

    override fun bind(): FragmentPlanetsBinding {
        return FragmentPlanetsBinding.inflate(layoutInflater)
    }

}