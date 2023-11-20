package com.example.pdfviewer

import android.content.Context
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import java.io.FileOutputStream

class PdfViewManager private constructor(
    private val context: Context
) {
    companion object {
        private var _instance: PdfViewManager? = null

        fun getInstance(context: Context): PdfViewManager {
            return if (_instance == null)
                PdfViewManager(context = context)
            else
                _instance!!
        }
    }

    private val scope =
        CoroutineScope(CoroutineName("File Manager scope") + SupervisorJob() + Dispatchers.Default)

    private val _renderer: MutableStateFlow<PdfRenderer?> = MutableStateFlow(null)
    val renderer: StateFlow<PdfRenderer?> = _renderer.asStateFlow()
    fun readPdfFile(path: String, savePath: String) {
        scope.launch(Dispatchers.IO) {
            val ins = context.assets.open(path)
            val file = File(savePath)
            try {
                val fos = FileOutputStream(file)
                val buffer = ByteArray(4096)
                while (true) {
                    val len = ins.read(buffer)
                    if (len == -1) break
                    fos.write(buffer, 0, len)
                }

                fos.flush()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            _renderer.value =
                PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY))

        }
    }
}