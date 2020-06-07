package org.kotlin.base_lib

import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Base class for Fragment(s) to be used across app. This enables registering fragments with Dagger (DI components).
 */
abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}
