package com.example.pdfviewer.adapter

import android.graphics.pdf.PdfRenderer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pdfviewer.R
import com.example.pdfviewer.viewholder.PdfViewerViewHolder

class PdfViewAdapter(
    private val activity: FragmentActivity,
    private val size: Int,
    private val block: (Int) -> Fragment
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = size

    override fun createFragment(position: Int): Fragment {
        return block(position)
    }
}