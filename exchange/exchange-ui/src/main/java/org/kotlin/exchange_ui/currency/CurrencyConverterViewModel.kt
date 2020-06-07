package org.kotlin.exchange_ui.currency

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kotlin.exchange_domain.callback.ResultCallback
import org.kotlin.exchange_domain.usecases.GetExchangeRatesUseCase
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

//View Model for handling currency conversions
class CurrencyConverterViewModel @Inject constructor(private val getExchangeRatesUseCase: GetExchangeRatesUseCase) :
    ViewModel(), Observable {

    var baseCurrency: String = ""
    var inputCurrencyType: String = ""

    @Bindable
    var inputCurrencyValue: String = ""
    var currencyNames: MutableList<String> = ArrayList()
    var currencyResult: ObservableField<String> = ObservableField("")
    val emptyValue: String = ""

    //Method to update currency type which will be converted to below currency
    fun updateConvertFromStr(pos: Int, callback: ResultCallback) {
        if (pos != 0) {
            baseCurrency = currencyNames.get(pos)
            viewModelScope.launch {
                getExchangeRatesUseCase.invoke(baseCurrency, callback)
            }
        }
    }

    //Method to update currency in which upper currency will be converted
    fun updateConvertToStr(pos: Int) {
        if (pos != 0) {
            inputCurrencyType = currencyNames.get(pos)
            convertCurrency()
        }
    }

    //Method to convert One currency to another
    fun convertCurrency() {
        if (!inputCurrencyType.isEmpty() && !inputCurrencyValue.isEmpty()) {
            currencyResult.set(emptyValue)
            val currencyValue = getExchangeRatesUseCase.getCurrencyValue(inputCurrencyType)
            currencyResult.set((BigDecimal(inputCurrencyValue.toDouble() * currencyValue).setScale(2, RoundingMode.HALF_EVEN)).toString())
        } else {
            currencyResult.set(emptyValue)
        }
    }

    //Method to fetch currency names from local Room Database
    fun getCurrencyNamesFromDb(defaultValue: String): List<Any> {
        currencyNames = getExchangeRatesUseCase.getCurrencyNames()
        currencyNames.add(0, defaultValue)
        return currencyNames
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        //TODO Do Nothing
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        //TODO Do Nothing
    }

}