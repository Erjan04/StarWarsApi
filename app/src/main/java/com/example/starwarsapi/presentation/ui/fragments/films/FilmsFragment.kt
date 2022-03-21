package com.example.starwarsapi.presentation.ui.fragments.films

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapi.databinding.FragmentFilmsBinding
import com.example.starwarsapi.domain.common.base.BaseFragment
import com.example.starwarsapi.domain.films.entity.FilmsEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FilmsFragment : BaseFragment<FragmentFilmsBinding>() {

    private val viewModel: FilmsViewModel by viewModels()
    private val adapter: FilmsAdapter by lazy {
        FilmsAdapter()
    }

    override fun setupObservers() {
        observeState()
        observeFilms()
    }

    private fun observeFilms() {
        viewModel.filmsList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleFilms(it) }.launchIn(lifecycleScope)
    }

    private fun handleFilms(films: List<FilmsEntity>) {
        adapter.submitList(films)
    }

    private fun observeState() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }.launchIn(lifecycleScope)
    }

    private fun handleState(state: FilmsViewModel.FilmsFragmentState) {
        when (state) {
            is FilmsViewModel.FilmsFragmentState.IsLoading -> handleLoading(state.isLoading)
            is FilmsViewModel.FilmsFragmentState.ShowToast -> Toast.makeText(
                requireContext(),
                state.message,
                Toast.LENGTH_SHORT
            ).show()
            is FilmsViewModel.FilmsFragmentState.Init -> Unit
        }
    }

    private fun handleLoading(loading: Boolean) {
        binding.progress.isVisible = loading
    }

    override fun setupUI() {
        binding.rvFilms.apply {
            adapter = this@FilmsFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun bind(): FragmentFilmsBinding {
        return FragmentFilmsBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {

    }
}