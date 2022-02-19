package com.whiterabbit.edata

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    protected abstract val viewModel: BaseViewModel
}