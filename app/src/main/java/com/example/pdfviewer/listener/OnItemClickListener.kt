package com.example.pdfviewer.listener

interface OnItemClickListener<T : Any> {
    fun onItemClick(item: T)
}