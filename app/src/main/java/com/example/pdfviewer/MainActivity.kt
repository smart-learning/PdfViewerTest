package com.example.pdfviewer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.pdfviewer.adapter.PdfViewAdapter
import com.example.pdfviewer.base.BaseActivity
import com.example.pdfviewer.databinding.ActivityMainBinding
import com.example.pdfviewer.fragment.PdfFragment
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

        observe()
    }

    private fun getRenderer() {
        vm.readPdfFile(path = "pdf/test3.pdf", savePath = cacheDir.absolutePath + "/test")
    }

    private fun bindRecyclerView() {
        with(binding.viewPager2) {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }


    private fun observe() {
        lifecycleScope.launch {
            //1회성
            vm.renderer.collectLatest {
                if (it == null) return@collectLatest

                withContext(Dispatchers.Main) {
                    binding.viewPager2.adapter = PdfViewAdapter(
                        activity = this@MainActivity,
                        size = it.pageCount
                    ) {
                        PdfFragment.newInstance(position = it)
                    }
                }

                cancel()
            }
        }
    }
}