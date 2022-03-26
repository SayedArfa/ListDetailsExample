package com.sarfa.listdetailsexample.core.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection

open class BaseFragment : Fragment() {
    protected var snackBar: Snackbar? = null
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        snackBar?.dismiss()
    }
}