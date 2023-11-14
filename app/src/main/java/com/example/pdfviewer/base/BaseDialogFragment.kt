package com.example.pdfviewer.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment<VB : ViewDataBinding>(private val layoutId: Int) :
    DialogFragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!


    open fun width() : Int = ViewGroup.LayoutParams.MATCH_PARENT
    open fun height() : Int = ViewGroup.LayoutParams.WRAP_CONTENT

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<VB>(inflater, layoutId, container, false).apply {
            _binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(width(),height())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}