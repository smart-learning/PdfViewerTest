package com.example.pdfviewer

import android.graphics.pdf.PdfRenderer
import androidx.lifecycle.ViewModel

class MainActivityViewModel(
    private val fm: PdfViewManager
) : ViewModel() {
<<<<<<< Updated upstream
    val descriptor = fm.descriptor
=======
    val renderer = fm.renderer
>>>>>>> Stashed changes

    fun readPdfFile(path: String, savePath: String) {
        fm.readPdfFile(path = path, savePath = savePath)
    }
}