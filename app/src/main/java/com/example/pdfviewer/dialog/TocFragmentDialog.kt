package com.example.pdfviewer.dialog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pdfviewer.MainActivityViewModel
import com.example.pdfviewer.R
import com.example.pdfviewer.adapter.PdfTocAdapter
import com.example.pdfviewer.adapter.PdfViewAdapter
import com.example.pdfviewer.base.BaseDialogFragment
import com.example.pdfviewer.databinding.DialogTocBinding
import com.example.pdfviewer.listener.OnItemClickListener
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TocFragmentDialog : BaseDialogFragment<DialogTocBinding>(R.layout.dialog_toc),
    OnItemClickListener<PDOutlineItem> {

    private val vm: MainActivityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.CREATED) {
                vm.document.collectLatest {
                    if (it == null) return@collectLatest

                    withContext(Dispatchers.Main) {
                        bindRecyclerView(outline = it.documentCatalog.documentOutline)
                    }
                }
            }
        }
    }

    private fun bindRecyclerView(outline: PDDocumentOutline) {
        with(binding.recyclerView) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            adapter = PdfTocAdapter(toc = outline.children().toList()).apply {
                setOnItemClickListener(this@TocFragmentDialog)
            }
        }
    }

    override fun onItemClick(item: PDOutlineItem) {
        Log.e("jms8732", "dest: ${item.destination}\n" +
                "cosObject: ${item.cosObject}"  )
    }
}