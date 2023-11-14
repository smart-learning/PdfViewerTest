package com.example.pdfviewer

import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pdfviewer.adapter.PdfViewAdapter
import com.example.pdfviewer.base.BaseActivity
import com.example.pdfviewer.databinding.ActivityMainBinding
import com.example.pdfviewer.dialog.TocFragmentDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val vm: MainActivityViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainActivityViewModel(
                    fm = PdfViewManager.getInstance(context = applicationContext)
                ) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getRenderer()
        bindRecyclerView()

        bindButton()
        observe()
    }

    private fun getRenderer() {
        vm.readPdfFile(path = "pdf/test2.pdf", savePath = cacheDir.absolutePath + "/test")
    }

    private fun bindRecyclerView() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)

            //Attach a LinearSnapHelper for snapping behavior
            val snapHelper = LinearSnapHelper();
            snapHelper.attachToRecyclerView(this);
        }
    }

    private fun bindButton(){
        binding.button.setOnClickListener {
            val dialog = TocFragmentDialog()
            dialog.show(supportFragmentManager,"dialog_toc")
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            //1회성
            vm.descriptor.collectLatest {
                if (it == null) return@collectLatest

                withContext(Dispatchers.Main) {
                    binding.recyclerView.adapter = PdfViewAdapter(
                        renderer = PdfRenderer(it),
                        pageWidth = resources.displayMetrics.widthPixels
                    )
                }

                cancel()
            }
        }
    }
}