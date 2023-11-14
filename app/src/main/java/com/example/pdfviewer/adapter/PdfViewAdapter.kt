package com.example.pdfviewer.adapter

import android.graphics.pdf.PdfRenderer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pdfviewer.R
import com.example.pdfviewer.viewholder.PdfViewerViewHolder

class PdfViewAdapter(
    private val renderer: PdfRenderer,
    private val pageWidth: Int
) : RecyclerView.Adapter<PdfViewerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfViewerViewHolder {
        return PdfViewerViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_pdf_viewer, parent, false),
            width = pageWidth,
            renderer = renderer
        )
    }

    override fun onBindViewHolder(holder: PdfViewerViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = renderer.pageCount
}