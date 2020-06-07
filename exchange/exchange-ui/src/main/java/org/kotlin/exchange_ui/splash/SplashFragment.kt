package org.kotlin.exchange_ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.kotlin.base_lib.BaseFragment
import org.kotlin.base_lib.ViewModelDelegate
import org.kotlin.exchange_ui.databinding.FragmentSplashBinding

//Splash View to manage the first Api call and fetch exchange data
class SplashFragment : BaseFragment() {

    private val viewModel: SplashViewModel by ViewModelDelegate()
    private lateinit var binding: FragmentSplashBinding
    private val SPLASH_TIME:Long=3000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater)
        viewModel.getExchangeList()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Handler().postDelayed({
            viewModel.error.value=false
            navigateToCurrencyConvertorFragment()
        }, SPLASH_TIME)
    }

    private fun navigateToCurrencyConvertorFragment() {
        val action = SplashFragmentDirections.actionSplashToCurrencyconvertor()
        findNavController().navigate(action)
    }
}



