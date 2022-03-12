package com.example.starwarsapi.presentation.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsapi.domain.people.entity.PeopleEntity
import com.example.starwarsapi.domain.people.usecases.GetPeopleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val getPeopleListUseCase: GetPeopleListUseCase) :
    ViewModel() {
    private val _peopleList = MutableStateFlow<List<PeopleEntity>>(mutableListOf())
    val peopleList: StateFlow<List<PeopleEntity>> get() = _peopleList

    private val _state = MutableStateFlow<PeopleFragmentState>(PeopleFragmentState.Init)
    val state: StateFlow<PeopleFragmentState> get() = _state

    init {
        fetchPeople()
    }

    private fun fetchPeople() {
        viewModelScope.launch {
            getPeopleListUseCase.invoke()
                .onStart {
                    setLoading()
                }
                .catch {
                    hideLoading()
                    showToast(it.message)
                }
                .collect {
                    hideLoading()
                    _peopleList.value = it
                }
        }
    }

    private fun showToast(message: String?) {
        _state.value = PeopleFragmentState.ShowToast(message)
    }

    private fun hideLoading() {
        _state.value = PeopleFragmentState.IsLoading(false)
    }

    private fun setLoading() {
        _state.value = PeopleFragmentState.IsLoading(true)
    }

    sealed class PeopleFragmentState {
        object Init : PeopleFragmentState()
        data class IsLoading(val isLoading: Boolean) : PeopleFragmentState()
        data class ShowToast(val message: String?) : PeopleFragmentState()

    }
}