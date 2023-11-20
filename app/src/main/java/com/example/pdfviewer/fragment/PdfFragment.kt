package com.example.pdfviewer.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.pdfviewer.MainActivityViewModel
import com.example.pdfviewer.R
import com.example.pdfviewer.base.BaseFragment
import com.example.pdfviewer.databinding.FragmentPdfBinding
import com.example.pdfviewer.extension.renderAndClose
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PdfFragment : BaseFragment<FragmentPdfBinding>(R.layout.fragment_pdf) {

    private val vm: MainActivityViewModel by activityViewModels()

    companion object {
        fun newInstance(position: Int): PdfFragment {
            return PdfFragment().apply {
                arguments = bundleOf(
                    "position" to position
                )
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindPage()
    }

    private fun bindPage() {
        val position = arguments?.getInt("position") ?: return
        val page = vm.renderer.value?.openPage(position) ?: return

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default) {
            val bm = page.renderAndClose(width = 360)

            withContext(Dispatchers.Main) {
                binding.imageViewPdf.setImageBitmap(bm)
            }
        }
    }
}