package com.example.pdfviewer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pdfviewer.R
import com.example.pdfviewer.viewholder.PdfViewerTocViewHolder

class PdfTocAdapter(private val toc: List<String>) :
    RecyclerView.Adapter<PdfViewerTocViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfViewerTocViewHolder {
        return PdfViewerTocViewHolder(
            view = LayoutInflater.from(parent.context).inflate(R.layout.cell_toc, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PdfViewerTocViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = toc.size
}