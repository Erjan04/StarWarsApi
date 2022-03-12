package com.example.starwarsapi.presentation.people

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapi.databinding.FragmentPeopleBinding
import com.example.starwarsapi.domain.common.base.BaseFragment
import com.example.starwarsapi.domain.people.entity.PeopleEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>() {

    private val viewModel: PeopleViewModel by viewModels()
    private val adapter: PeopleAdapter by lazy {
        PeopleAdapter()
    }

    override fun setupObservers() {
        observeState()
        observePeoples()
    }

    private fun observeState() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).onEach {
            handleState(it)
        }.launchIn(lifecycleScope)
    }

    private fun handleState(state: PeopleViewModel.PeopleFragmentState) {
        when (state) {
            is PeopleViewModel.PeopleFragmentState.IsLoading -> handleLoading(state.isLoading)
            is PeopleViewModel.PeopleFragmentState.ShowToast -> Toast.makeText(
                requireContext(),
                state.message,
                Toast.LENGTH_SHORT
            ).show()
            is PeopleViewModel.PeopleFragmentState.Init -> Unit
        }
    }

    private fun handleLoading(loading: Boolean) {
        binding.progress.isVisible = loading
    }

    private fun observePeoples() {
        viewModel.peopleList.flowWithLifecycle(
            lifecycle, Lifecycle.State.STARTED
        ).onEach {
            handlePeoples(it)
        }.launchIn(lifecycleScope)
    }

    private fun handlePeoples(people: List<PeopleEntity>) {
        adapter.submitList(people)
    }

    override fun setupUI() {
        binding.rvPeople.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PeopleFragment.adapter
        }
    }

    override fun bind(): FragmentPeopleBinding {
        return FragmentPeopleBinding.inflate(layoutInflater)
    }
}
