package org.kotlin.exchange_ui.splash


import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
 import kotlinx.coroutines.launch
import org.kotlin.exchange_domain.usecases.GetExchangeRatesUseCase
import javax.inject.Inject

//ViewModel for fetching data from Api for the first time
class SplashViewModel @Inject constructor(
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase
) : ViewModel() {

    var error: MutableLiveData<Boolean> =MutableLiveData(false)

    fun getExchangeList() {
        viewModelScope.launch {
            getExchangeRatesUseCase.invoke("CAD",null)
        }
    }

}