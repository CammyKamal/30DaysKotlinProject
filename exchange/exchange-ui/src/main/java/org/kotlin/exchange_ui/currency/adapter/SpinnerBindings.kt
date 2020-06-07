package org.kotlin.exchange_ui.currency.adapter

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import org.kotlin.exchange_ui.currency.adapter.SpinnerExtensions.setSpinnerEntries

//File containing data binding adapter for Spinners
@BindingAdapter("entries")
fun Spinner.setEntries(entries: List<Any>?) {
    setSpinnerEntries(entries)
}
