package com.example.starwarsapi.presentation.starships

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapi.domain.starships.entity.StarShipsEntity
import com.example.starwarsapi.domain.starships.usecases.GetStarShipsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarShipsViewModel @Inject constructor(private val getStarShipsUseCase: GetStarShipsUseCase) :
    ViewModel() {

    private val _starShipsList = MutableStateFlow<List<StarShipsEntity>>(mutableListOf())
    val starShipsList: StateFlow<List<StarShipsEntity>> get() = _starShipsList

    private val _state =
        MutableStateFlow<StarShipsFragmentState>(StarShipsFragmentState.Init)
    val state: StateFlow<StarShipsFragmentState> get() = _state

    init {
        fetchStarShips()
    }

    private fun fetchStarShips() {
        viewModelScope.launch {
            getStarShipsUseCase.invoke()
                .onStart {
                    setLoading()
                }.catch {
                    hideLoading()
                    showToast(it.message)
                }.collect {
                    hideLoading()
                    _starShipsList.value = it
                }
        }
    }

    private fun showToast(message: String?) {
        _state.value = StarShipsFragmentState.ShowToast(message)
    }

    private fun hideLoading() {
        _state.value = StarShipsFragmentState.IsLoading(false)
    }

    private fun setLoading() {
        _state.value = StarShipsFragmentState.IsLoading(true)
    }

    sealed class StarShipsFragmentState {
        object Init : StarShipsFragmentState()
        data class IsLoading(val isLoading: Boolean) : StarShipsFragmentState()
        data class ShowToast(val message: String?) : StarShipsFragmentState()

    }
}