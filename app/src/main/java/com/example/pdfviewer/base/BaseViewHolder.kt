package com.example.pdfviewer.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<VB : ViewDataBinding>(private val view: View) :
    RecyclerView.ViewHolder(view) {
    protected val binding = DataBindingUtil.bind<VB>(view)!!
}