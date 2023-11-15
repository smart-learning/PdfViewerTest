package com.example.pdfviewer.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.pdfviewer.MainActivityViewModel
import com.example.pdfviewer.R
import com.example.pdfviewer.base.BaseDialogFragment
import com.example.pdfviewer.databinding.DialogTocBinding
import kotlinx.coroutines.launch

class TocFragmentDialog : BaseDialogFragment<DialogTocBinding>(R.layout.dialog_toc){

    private val vm: MainActivityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.CREATED) {
            }
        }
    }
}