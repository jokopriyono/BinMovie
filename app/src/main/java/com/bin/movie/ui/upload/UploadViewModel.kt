package com.bin.movie.ui.upload

import android.webkit.MimeTypeMap
import androidx.lifecycle.MutableLiveData
import com.bin.movie.base.BaseViewModel
import com.bin.movie.data.model.remote.upload.UploadResponse
import com.bin.movie.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {

    val uploadResponse = MutableLiveData<UploadResponse>()
    suspend fun uploadImageToServer(file: File) {
        val url = "https://store1.gofile.io/uploadFile"
        val token = "MycC2HEmsOPrnbNSrCbCZ2Q7MRUnRhBN"
        val fileRequestBody = file.asRequestBody(
            getMimeType(file.path)!!.toMediaType()
        )
        // contoh untuk melampirkan file di form-data
        val fileMultiPart = MultipartBody.Part.createFormData(
            "file",
            file.name, fileRequestBody
        )
        // contoh untuk melampirkan text di form-data
        val tokenRequestBody = token.toRequestBody(
            "text/plain".toMediaType()
        )
        mainRepository.uploadFileAndGetResult(
            onStart = { _loading.postValue(true) },
            onComplete = { _loading.postValue(false) },
            onError = { _message.postValue(it) },
            url,
            fileMultiPart,
            tokenRequestBody
        ).collect {
            uploadResponse.postValue(it)
        }
    }

    private fun getMimeType(path: String): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(path)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }
}
