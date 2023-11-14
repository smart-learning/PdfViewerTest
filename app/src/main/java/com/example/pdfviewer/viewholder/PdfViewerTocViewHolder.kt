package com.example.pdfviewer.viewholder

import android.view.View
import com.example.pdfviewer.R
import com.example.pdfviewer.base.BaseViewHolder
import com.example.pdfviewer.databinding.CellTocBinding
import com.example.pdfviewer.listener.OnItemClickListener
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem

class PdfViewerTocViewHolder(
    private val view: View,
    private val listener: OnItemClickListener<PDOutlineItem>?
) : BaseViewHolder<CellTocBinding>(view = view) {

    private lateinit var item: PDOutlineItem

    init {
        binding.root.setOnClickListener {
            listener?.onItemClick(item = item)
        }
    }

    fun bind(item: PDOutlineItem) {
        this.item = item
        binding.textViewTitle.text = item.title
    }
}