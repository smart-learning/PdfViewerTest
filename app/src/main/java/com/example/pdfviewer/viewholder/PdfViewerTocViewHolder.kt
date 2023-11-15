package com.example.pdfviewer.viewholder

import android.view.View
import com.example.pdfviewer.base.BaseViewHolder
import com.example.pdfviewer.databinding.CellTocBinding

class PdfViewerTocViewHolder(
    private val view: View,
) : BaseViewHolder<CellTocBinding>(view = view) {

    init {
        binding.root.setOnClickListener {
        }
    }
}