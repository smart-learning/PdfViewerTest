package com.example.pdfviewer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pdfviewer.R
import com.example.pdfviewer.listener.OnItemClickListener
import com.example.pdfviewer.viewholder.PdfViewerTocViewHolder
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem

class PdfTocAdapter(private val toc: List<PDOutlineItem>) :
    RecyclerView.Adapter<PdfViewerTocViewHolder>() {

    private var _listener: OnItemClickListener<PDOutlineItem>? = null
    fun setOnItemClickListener(listener: OnItemClickListener<PDOutlineItem>?) {
        _listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfViewerTocViewHolder {
        return PdfViewerTocViewHolder(
            view = LayoutInflater.from(parent.context).inflate(R.layout.cell_toc, parent, false),
            listener = _listener
        )
    }

    override fun onBindViewHolder(holder: PdfViewerTocViewHolder, position: Int) {
        holder.bind(item = toc.get(position))
    }

    override fun getItemCount(): Int = toc.size
}