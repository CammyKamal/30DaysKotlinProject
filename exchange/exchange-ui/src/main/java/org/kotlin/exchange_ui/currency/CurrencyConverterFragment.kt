package org.kotlin.exchange_ui.currency

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import org.kotlin.base_lib.BaseFragment
import org.kotlin.base_lib.ViewModelDelegate
import org.kotlin.exchange_domain.callback.ResultCallback
import org.kotlin.exchange_ui.R
import org.kotlin.exchange_ui.currency.adapter.setEntries
import org.kotlin.exchange_ui.databinding.FragmentCurrencyConvertorBinding

//Currency Converter Fragment
class CurrencyConverterFragment : BaseFragment(), ResultCallback {

    private val viewModel: CurrencyConverterViewModel by ViewModelDelegate()
    private lateinit var binding: FragmentCurrencyConvertorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCurrencyConvertorBinding.inflate(inflater).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        viewModel.getCurrencyNamesFromDb(activity!!.getString(R.string.Select))
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.inputcurrencySpinner.setEntries(viewModel.currencyNames)
        binding.resultcurrencySpinner.setEntries(viewModel.currencyNames)
        setSpinnerItemClickListeners()
    }

    private fun setSpinnerItemClickListeners() {
        binding.inputcurrencySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                viewModel.updateConvertFromStr(position, this@CurrencyConverterFragment)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //TODO
            }
        }

        binding.resultcurrencySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                Log.e("", "")
                viewModel.updateConvertToStr(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //TODO
            }
        }
    }

    override fun success() {
        viewModel.convertCurrency()
    }

    override fun failure() {
        TODO("Not yet implemented")
    }
}