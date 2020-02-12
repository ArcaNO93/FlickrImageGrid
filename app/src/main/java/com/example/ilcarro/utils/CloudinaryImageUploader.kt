package com.example.ilcarro.utils

import android.net.Uri
import android.util.Log
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.example.ilcarro.dagger.scopes.GlobalScope
import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUI
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.SingleSubject
import okio.IOException
import java.io.File
import javax.inject.Inject

@GlobalScope
class CloudinaryImageUploader @Inject constructor(
    private val mediaManager: MediaManager
) {
    private val mEmitter = BehaviorSubject.create<String>()
    val mUploadImageProcess: Observable<String> = mEmitter

    private val callBack = object : UploadCallback {
        override fun onSuccess(requestId: String?, resultData: MutableMap<Any?, Any?>?) {
            mEmitter.onNext(resultData?.get("url") as String)
        }

        override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {
            Log.d("tag", "onProgress")
        }

        override fun onReschedule(requestId: String?, error: ErrorInfo?) {
            Log.d("tag", "onReschedule")
        }

        override fun onError(requestId: String?, error: ErrorInfo?) {
            mEmitter.onError(IOException(error?.description))
        }

        override fun onStart(requestId: String?) {
            Log.d("tag", "onStart")
        }
    }

    fun uploadImage(image: String, carUI: AddCarUI) {
        mediaManager.upload(Uri.parse(image))
            .option(
                "public_id",
                "${carUI.make}_${carUI.model}_photo_${Math.random() * 10}"
            )
            .option("resource_type", "auto")
            .option("folder", "ilCarro/${carUI.make}_${carUI.model}")
            .option("overwrite", true)
            .option("connect_timeout", 10000)
            .option("read_timeout", 10000)
            .callback(callBack)
            .dispatch()
    }
}
