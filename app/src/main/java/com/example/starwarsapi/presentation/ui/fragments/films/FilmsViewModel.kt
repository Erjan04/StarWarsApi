package com.example.starwarsapi.presentation.ui.fragments.films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapi.domain.films.entity.FilmsEntity
import com.example.starwarsapi.domain.films.usecases.GetFilmsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(private val getFilmsListUseCase: GetFilmsListUseCase) :
    ViewModel() {

    private val _filmsList = MutableStateFlow<List<FilmsEntity>>(mutableListOf())
    val filmsList: StateFlow<List<FilmsEntity>> get() = _filmsList

    private val _state = MutableStateFlow<FilmsFragmentState>(FilmsFragmentState.Init)
    val state: StateFlow<FilmsFragmentState> get() = _state

    init {
        fetchFilms()
    }

    private fun fetchFilms() {
        viewModelScope.launch {
            getFilmsListUseCase.invoke()
                .onStart {
                    setLoading()
                }.catch {
                    hideLoading()
                    showToast(it.message)
                }.collect {
                    hideLoading()
                    _filmsList.value = it
                }
        }
    }

    private fun showToast(message: String?) {
        _state.value = FilmsFragmentState.ShowToast(message)
    }

    private fun hideLoading() {
        _state.value = FilmsFragmentState.IsLoading(false)
    }

    private fun setLoading() {
        _state.value = FilmsFragmentState.IsLoading(true)
    }

    sealed class FilmsFragmentState {
        object Init : FilmsFragmentState()
        data class IsLoading(val isLoading: Boolean) : FilmsFragmentState()
        data class ShowToast(val message: String?) : FilmsFragmentState()
    }

}