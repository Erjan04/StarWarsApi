package com.example.starwarsapi.presentation.ui.fragments.people

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.starwarsapi.domain.common.base.BaseViewModel
import com.example.starwarsapi.domain.people.usecases.GetPeopleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val getPeopleListUseCase: GetPeopleListUseCase) :
    BaseViewModel() {

//    private val _peopleList = MutableStateFlow<List<PeopleEntity>>(mutableListOf())
//    val peopleList: StateFlow<List<PeopleEntity>> get() = _peopleList

//    private val _state = MutableStateFlow<PeopleFragmentState>(PeopleFragmentState.Init)
//    val state: StateFlow<PeopleFragmentState> get() = _state

    fun fetchPeople() = getPeopleListUseCase().cachedIn(viewModelScope)

//    init {
//        fetchStatePeople()
//    }
//
//    private fun fetchStatePeople() {
//        viewModelScope.launch {
//            getPeopleListUseCase.invoke()
//                .onStart {
//                    setLoading()
//                }.catch {
//                    hideLoading()
//                    showToast(it.message)
//                }.collect {
//                    hideLoading()
//                }
//        }
//    }
//
//    private fun showToast(message: String?) {
//        _state.value = PeopleFragmentState.ShowToast(message)
//    }
//
//    private fun hideLoading() {
//        _state.value = PeopleFragmentState.IsLoading(false)
//    }
//
//    private fun setLoading() {
//        _state.value = PeopleFragmentState.IsLoading(true)
//    }
//
//    sealed class PeopleFragmentState {
//        object Init : PeopleFragmentState()
//        data class IsLoading(val isLoading: Boolean) : PeopleFragmentState()
//        data class ShowToast(val message: String?) : PeopleFragmentState()
//    }
}