package com.example.demo.ui

import androidx.lifecycle.*
import com.example.demo.data.CurrencyDatabase
import com.example.demo.data.CurrencyInfo
import com.example.demo.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyDatabase: CurrencyDatabase,
    private val dispatcherProvider: DispatcherProvider,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _currencyList = MutableLiveData<List<CurrencyInfo>>()
    val currencyList: LiveData<List<CurrencyInfo>> = _currencyList

    private val _selectedCurrency = MutableLiveData<CurrencyInfo>()
    val selectedCurrency: LiveData<CurrencyInfo> = _selectedCurrency

    private var loadJob: Job? = null
    private var sortJob: Job? = null

    fun loadCurrencyList() {
        loadJob?.cancel() // cancel current running job if exists

        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable)
        }

        loadJob = viewModelScope.launch(dispatcherProvider.io + coroutineExceptionHandler) {
            val list = currencyDatabase.currencyDao().getAll()
            _currencyList.postValue(list)
        }
    }

    fun sortCurrencyList() {
        sortJob?.cancel() // cancel current running job if exists

        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable)
        }

        sortJob = viewModelScope.launch(dispatcherProvider.io + coroutineExceptionHandler) {
            val currentDisplayedList = currencyList.value
            if (currentDisplayedList != null) {
                val sortedList = currentDisplayedList.sortedBy { it.name }
                _currencyList.postValue(sortedList)
            }
        }
    }

    fun selectCurrency(currencyInfo: CurrencyInfo) {
        _selectedCurrency.value = currencyInfo
    }
}