package com.example.pdfviewer

import android.content.res.AssetFileDescriptor
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pdfviewer.adapter.PdfViewAdapter
import com.example.pdfviewer.base.BaseActivity
import com.example.pdfviewer.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val vm: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getRenderer()
        bindRecyclerView()
    }

    private fun getRenderer() {
        lifecycleScope.launch(Dispatchers.IO) {
            readFile()
        }
    }

    private suspend fun readFile() {
        withContext(Dispatchers.IO) {
            val ins = assets.open("pdf/test.pdf")
            val file = File(cacheDir.absolutePath + "/test")
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

            val descriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)

            withContext(Dispatchers.Main) {
                binding.recyclerView.adapter = PdfViewAdapter(
                    renderer = PdfRenderer(descriptor),
                    pageWidth = resources.displayMetrics.widthPixels
                )
            }
        }
    }

    private fun bindRecyclerView() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)

            //Attach a LinearSnapHelper for snapping behavior
            val snapHelper = LinearSnapHelper();
            snapHelper.attachToRecyclerView(this);
        }
    }
}