package com.bharatdodeja.mycv.util

import android.os.Environment.DIRECTORY_PICTURES
import android.os.Environment.getExternalStoragePublicDirectory
import androidx.test.runner.screenshot.BasicScreenCaptureProcessor
import java.io.File

class MyScreenCaptureProcessor(parentFolderPath: String) : BasicScreenCaptureProcessor() {

    init {
        this.mDefaultScreenshotPath = File(
                File(
                        getExternalStoragePublicDirectory(DIRECTORY_PICTURES),
                        ""
                ).absolutePath,
                "/$parentFolderPath"
        )
    }

    override fun getFilename(prefix: String): String = prefix
}
