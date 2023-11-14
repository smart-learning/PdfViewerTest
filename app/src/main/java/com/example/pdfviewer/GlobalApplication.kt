package com.example.pdfviewer

import android.app.Application
import android.content.Context
import android.provider.Settings.Global
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PDFBoxResourceLoader.init(applicationContext)
    }
}