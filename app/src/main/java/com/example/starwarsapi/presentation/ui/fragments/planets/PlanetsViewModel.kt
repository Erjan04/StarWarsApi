package com.example.starwarsapi.presentation.ui.fragments.planets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapi.domain.planets.entity.PlanetsEntity
import com.example.starwarsapi.domain.planets.usecases.GetPlanetsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(private val getPlanetsListUseCase: GetPlanetsListUseCase) :
    ViewModel() {

    private val _planetsList = MutableStateFlow<List<PlanetsEntity>>(mutableListOf())
    val planetsList: StateFlow<List<PlanetsEntity>> get() = _planetsList

    private val _state =
        MutableStateFlow<PlanetsFragmentState>(PlanetsFragmentState.Init)
    val state: StateFlow<PlanetsFragmentState> get() = _state

    init {
        fetchPlanets()
    }

    private fun fetchPlanets() {
        viewModelScope.launch {
            getPlanetsListUseCase.invoke()
                .onStart {
                    setLoading()
                }.catch {
                    hideLoading()
                    showToast(it.message)
                }.collect {
                    hideLoading()
                    _planetsList.value = it
                }
        }
    }

    private fun showToast(message: String?) {
        _state.value = PlanetsFragmentState.ShowToast(message)
    }

    private fun hideLoading() {
        _state.value = PlanetsFragmentState.IsLoading(false)
    }

    private fun setLoading() {
        _state.value = PlanetsFragmentState.IsLoading(true)
    }

    sealed class PlanetsFragmentState {
        object Init : PlanetsFragmentState()
        data class IsLoading(val isLoading: Boolean) : PlanetsFragmentState()
        data class ShowToast(val message: String?) : PlanetsFragmentState()

    }

}