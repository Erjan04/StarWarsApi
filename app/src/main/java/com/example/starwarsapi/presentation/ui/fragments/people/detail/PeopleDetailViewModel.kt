package com.example.starwarsapi.presentation.ui.fragments.people.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.domain.common.base.BaseViewModel
import com.example.starwarsapi.domain.people.entity.PeopleEntity
import com.example.starwarsapi.domain.people.usecases.GetPeopleDetailUseCase
import com.example.starwarsapi.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleDetailViewModel
@Inject constructor(private val getPeopleDetailUseCase: GetPeopleDetailUseCase) : BaseViewModel() {

    private val _state = MutableLiveData<UIState<PeopleEntity>>()
    val state: LiveData<UIState<PeopleEntity>> = _state

    fun getData(id: Int) {
        stateData(
            _state
        ) {
            getPeopleDetailUseCase(id)
        }
    }

}