package com.android.greencarot.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.greencarot.model.User
import com.android.greencarot.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {


    private val _names = MutableStateFlow<User?>(null)
    val name: StateFlow<User?> = _names

    init {
        getNames(30, 1)
    }



    private fun recall(size: Long, index: Long) {
        getNames(size, index)
    }

    /**
     * Params
     * [size] Total number of names to be pulled down from endpoint
     * [startIndex] Starting index to be pulled
     */
    private fun getNames(size: Long, startIndex: Long) {
        viewModelScope.launch {
            repository.getName(startIndex)
                .collect { user ->
                    if (startIndex <= size) {
                        if (user != null) {
                            _names.value = user
                            recall(size, startIndex + 1)
                        }
                    }

                }

        }
    }


}

