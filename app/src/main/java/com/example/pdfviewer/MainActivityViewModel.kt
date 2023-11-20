package com.example.pdfviewer

import androidx.lifecycle.ViewModel

class MainActivityViewModel(
    private val fm: PdfViewManager
) : ViewModel() {

    val renderer = fm.renderer

    fun readPdfFile(path: String, savePath: String) {
        fm.readPdfFile(path = path, savePath = savePath)
    }
}