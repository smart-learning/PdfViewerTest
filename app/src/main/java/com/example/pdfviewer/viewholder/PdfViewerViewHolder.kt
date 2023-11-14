package com.example.pdfviewer.viewholder

import android.graphics.pdf.PdfRenderer
import android.view.View
import com.example.pdfviewer.base.BaseViewHolder
import com.example.pdfviewer.databinding.CellPdfViewerBinding
import com.example.pdfviewer.extension.renderAndClose

class PdfViewerViewHolder(
    private val view: View,
    private val renderer: PdfRenderer,
    private val width: Int
) :
    BaseViewHolder<CellPdfViewerBinding>(view = view) {

    fun bind(position: Int) {
        binding.imageView.setImageBitmap(renderer.openPage(position).renderAndClose(width = width))
    }
}