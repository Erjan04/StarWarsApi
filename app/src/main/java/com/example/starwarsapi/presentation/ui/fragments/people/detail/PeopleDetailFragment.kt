package com.example.starwarsapi.presentation.ui.fragments.people.detail

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.starwarsapi.databinding.FragmentPeopleDetailBinding
import com.example.starwarsapi.domain.common.base.BaseFragment
import com.example.starwarsapi.domain.common.utils.extension.showToast
import com.example.starwarsapi.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleDetailFragment : BaseFragment<FragmentPeopleDetailBinding>() {

    private val viewModel: PeopleDetailViewModel by viewModels()
    private val args: PeopleDetailFragmentArgs by navArgs()

    override fun setupObservers() {
        viewModel.state.observe(this) {
            when (it) {
                is UIState.Loading -> {
                    binding.progress.isVisible = true
                }
                is UIState.Error -> {
                    binding.progress.isVisible = false
                    requireContext().showToast("Error")
                }
                is UIState.Success -> {
                    binding.progress.isVisible = false
                    binding.tvName.text = it.data.name
                    binding.tvGender.text = it.data.gender
                    binding.tvMass.text = it.data.mass
                    binding.tvHairColor.text = it.data.hairColor
                }
            }
        }
    }

    override fun setupUI() {
        viewModel.getData(args.id)
    }

    override fun bind(): FragmentPeopleDetailBinding =
        FragmentPeopleDetailBinding.inflate(layoutInflater)

    override fun setupListeners() {

    }

}