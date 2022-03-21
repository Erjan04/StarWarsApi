package com.example.starwarsapi.presentation.ui.fragments.people

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapi.databinding.FragmentPeopleBinding
import com.example.starwarsapi.domain.common.base.BaseFragment
import com.example.starwarsapi.domain.common.utils.extension.stateLoad
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
        observePeoples()
    }

    override fun setupListeners() {
        adapter.onItemClickListener = {
            findNavController().navigate(
                PeopleFragmentDirections.actionPeopleFragmentToPeopleDetailFragment(
                    it.id
                )
            )
        }
    }

    private fun observePeoples() {
        viewModel.fetchPeople().flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handlePeoples(it) }.launchIn(lifecycleScope)
    }

    private suspend fun handlePeoples(pagingData: PagingData<PeopleEntity>) {
        adapter.submitData(pagingData)
    }

    override fun setupUI() {
        binding.rvPeople.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PeopleFragment.adapter
        }
        adapter.stateLoad(binding.rvPeople, binding.progress)
    }

    override fun bind(): FragmentPeopleBinding {
        return FragmentPeopleBinding.inflate(layoutInflater)
    }
}
