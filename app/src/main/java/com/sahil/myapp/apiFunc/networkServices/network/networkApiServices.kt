import com.sahil.myapp.apiFunc.networkServices.network.DownloadException
import com.sahil.myapp.apiFunc.networkServices.network.FetchDataException
import com.sahil.myapp.apiFunc.networkServices.network.UploadException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream

class NetworkApiService : BaseApiService {

    private val client = OkHttpClient()

    override suspend fun getApi(
        url: String,
        sendHeader: Boolean,
        headerMap: Map<String, String>?
    ): Any = withContext(Dispatchers.IO) {
        val requestBuilder = Request.Builder().url(url)
        if (sendHeader && headerMap != null) {
            for ((key, value) in headerMap) {
                requestBuilder.addHeader(key, value)
            }
        }
        val request = requestBuilder.build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) throw FetchDataException("GET failed", response.code)
        response.body?.string() ?: throw FetchDataException("Empty response")
    }

    override suspend fun postApi(
        url: String,
        data: Any?,
        sendHeader: Boolean,
        headerMap: Map<String, String>?
    ): Any = withContext(Dispatchers.IO) {
        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()

        val body = if (data != null) {
            data.toString().toRequestBody(mediaType)
        } else {
            "".toRequestBody(mediaType)
        }

        val requestBuilder = Request.Builder().url(url).post(body)

        if (sendHeader && headerMap != null) {
            for ((key, value) in headerMap) {
                requestBuilder.addHeader(key, value)
            }
        }

        val request = requestBuilder.build()

        // 🔍 LOGGING START
        println("➡️ POST Request URL: $url")
        println("➡️ Headers: ${request.headers}")
        println("➡️ Body: ${body.contentLength()} bytes")
        // 🔍 LOGGING END

        val response = client.newCall(request).execute()

        val responseBody = response.body?.string()

        // 🔍 LOGGING RESPONSE
        println("⬅️ Response Code: ${response.code}")
        println("⬅️ Response Body: $responseBody")

        if (!response.isSuccessful) throw FetchDataException("POST failed", response.code)

        return@withContext responseBody ?: throw FetchDataException("Empty response")
    }


    override suspend fun patchApi(
        url: String,
        data: Any?,
        sendHeader: Boolean,
        headerMap: Map<String, String>?
    ): Any = withContext(Dispatchers.IO) {
        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val body = (data.toString()).toRequestBody(mediaType)
        val requestBuilder = Request.Builder().url(url).patch(body)
        if (sendHeader && headerMap != null) {
            for ((key, value) in headerMap) {
                requestBuilder.addHeader(key, value)
            }
        }
        val request = requestBuilder.build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) throw FetchDataException("PATCH failed", response.code)
        response.body?.string() ?: throw FetchDataException("Empty response")
    }

    override suspend fun putApi(
        url: String,
        data: Any?,
        sendHeader: Boolean,
        headerMap: Map<String, String>?
    ): Any = withContext(Dispatchers.IO) {
        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val body = (data.toString()).toRequestBody(mediaType)
        val requestBuilder = Request.Builder().url(url).put(body)
        if (sendHeader && headerMap != null) {
            for ((key, value) in headerMap) {
                requestBuilder.addHeader(key, value)
            }
        }
        val request = requestBuilder.build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) throw FetchDataException("PUT failed", response.code)
        response.body?.string() ?: throw FetchDataException("Empty response")
    }

    override suspend fun deleteApi(
        url: String,
        data: Any?,
        sendHeader: Boolean,
        headerMap: Map<String, String>?
    ): Any = withContext(Dispatchers.IO) {
        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val body = (data.toString()).toRequestBody(mediaType)
        val requestBuilder = Request.Builder().url(url).delete(body)
        if (sendHeader && headerMap != null) {
            for ((key, value) in headerMap) {
                requestBuilder.addHeader(key, value)
            }
        }
        val request = requestBuilder.build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) throw FetchDataException("DELETE failed", response.code)
        response.body?.string() ?: throw FetchDataException("Empty response")
    }

    override suspend fun uploadFile(
        url: String,
        filePath: String,
        fieldName: String,
        headers: Map<String, String>?,
        fields: Map<String, String>?,
        method: String,
        onProgress: ((sent: Int, total: Int) -> Unit)?
    ): Any = withContext(Dispatchers.IO) {
        val file = File(filePath)
        val body = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart(fieldName, file.name, body)

        fields?.forEach { (key, value) ->
            multipartBody.addFormDataPart(key, value)
        }

        val requestBuilder = Request.Builder().url(url)
        if (headers != null) {
            for ((key, value) in headers) {
                requestBuilder.addHeader(key, value)
            }
        }

        when (method.uppercase()) {
            "PUT" -> requestBuilder.put(multipartBody.build())
            "PATCH" -> requestBuilder.patch(multipartBody.build())
            else -> requestBuilder.post(multipartBody.build())
        }

        val response = client.newCall(requestBuilder.build()).execute()
        if (!response.isSuccessful) throw UploadException("Upload failed: ${response.code}")
        response.body?.string() ?: throw UploadException("Empty upload response")
    }

    override suspend fun uploadMultipart(
        url: String,
        headers: Map<String, String>,
        fields: Map<String, String>,
        files: Map<String, String>
    ): Any = withContext(Dispatchers.IO) {
        val multipartBody = MultipartBody.Builder().setType(MultipartBody.FORM)
        fields.forEach { (key, value) ->
            multipartBody.addFormDataPart(key, value)
        }
        files.forEach { (fieldName, filePath) ->
            val file = File(filePath)
            val body = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            multipartBody.addFormDataPart(fieldName, file.name, body)
        }

        val requestBuilder = Request.Builder().url(url).post(multipartBody.build())
        headers.forEach { (key, value) ->
            requestBuilder.addHeader(key, value)
        }

        val response = client.newCall(requestBuilder.build()).execute()
        if (!response.isSuccessful) throw UploadException("Multipart upload failed: ${response.code}")
        response.body?.string() ?: throw UploadException("Empty multipart response")
    }

    override suspend fun downloadFile(
        url: String,
        savePath: String,
        headers: Map<String, String>?,
        onProgress: ((received: Int, total: Int) -> Unit)?
    ): Any = withContext(Dispatchers.IO) {
        val requestBuilder = Request.Builder().url(url)
        headers?.forEach { (key, value) ->
            requestBuilder.addHeader(key, value)
        }
        val request = requestBuilder.build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) throw DownloadException("Download failed: ${response.code}")

        val inputStream = response.body?.byteStream() ?: throw DownloadException("Empty download stream")
        val totalBytes = response.body?.contentLength()?.toInt() ?: -1
        val outputFile = File(savePath)
        val outputStream = FileOutputStream(outputFile)
        val buffer = ByteArray(2048)
        var bytesRead: Int
        var downloaded = 0

        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            outputStream.write(buffer, 0, bytesRead)
            downloaded += bytesRead
            onProgress?.invoke(downloaded, totalBytes)
        }

        outputStream.flush()
        outputStream.close()
        inputStream.close()

        return@withContext savePath
    }

    override fun dispose() {
        client.dispatcher.executorService.shutdown()
    }
}
