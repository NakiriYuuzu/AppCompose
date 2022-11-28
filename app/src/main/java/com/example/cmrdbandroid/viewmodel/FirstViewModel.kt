package com.example.cmrdbandroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmrdbandroid.model.FirstModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FirstViewModel: ViewModel() {

    private val _state = MutableStateFlow(FirstViewState())
    fun consumeState() = _state.asStateFlow()

    init {
        initData()
    }

    fun onEvent(event: FirstEvent) {
        when (event) {
            is FirstEvent.OnClick -> {
                Log.e("TAG", "onEvent: $event")
                val currentState = _state.value
                val data = currentState.data.toMutableList().apply {
                    val randomNumber = (0..100).random()
                    add(FirstModel(title = "Title $randomNumber", content = "Content $randomNumber"))
                }.toList()
                _state.value = _state.value.copy(data = data)
            }
            is FirstEvent.OnLongClick -> {
                Log.e("TAG", "onEvent: $event")
                val currentState = _state.value
                val data = currentState.data.toMutableList().apply {
                    remove(event.firstModel)
                }.toList()
                _state.value = _state.value.copy(data = data)
            }
        }
    }

    private fun initData() {
        viewModelScope.launch {
            val myData = mutableListOf(
                FirstModel("Title 1", "Content 1"),
                FirstModel("Title 2", "Content 2"),
                FirstModel("Title 3", "Content 3"),
                FirstModel("Title 4", "Content 4"),
                FirstModel("Title 5", "Content 5"),
                FirstModel("Title 6", "Content 6"),
                FirstModel("Title 7", "Content 7"),
                FirstModel("Title 8", "Content 8"),
                FirstModel("Title 9", "Content 9"),
                FirstModel("Title 10", "Content 10"),
                FirstModel("Title 11", "Content 11"),
                FirstModel("Title 12", "Content 12"),
                FirstModel("Title 13", "Content 13"),
                FirstModel("Title 14", "Content 14"),
                FirstModel("Title 15", "Content 15"),
                FirstModel("Title 16", "Content 16"),
            )
            _state.value = _state.value.copy(data = myData)
        }
    }
}

data class FirstViewState(val data: List<FirstModel> = emptyList())

sealed class FirstEvent {
    data class OnClick(val firstModel: FirstModel): FirstEvent()
    data class OnLongClick(val firstModel: FirstModel): FirstEvent()
}