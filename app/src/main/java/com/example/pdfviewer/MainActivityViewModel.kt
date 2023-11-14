package com.example.pdfviewer

import androidx.lifecycle.ViewModel

class MainActivityViewModel(
    private val fm: PdfViewManager
) : ViewModel() {
    val descriptor = fm.descriptor
    val document = fm.document

    fun readPdfFile(path: String, savePath: String) {
        fm.readPdfFile(path = path, savePath = savePath)
    }
}