interface BaseApiService {
    suspend fun getApi(url: String, sendHeader: Boolean, headerMap: Map<String, String>? = null): Any
    suspend fun postApi(url: String, data: Any?, sendHeader: Boolean, headerMap: Map<String, String>? = null): Any
    suspend fun patchApi(url: String, data: Any?, sendHeader: Boolean, headerMap: Map<String, String>? = null): Any
    suspend fun putApi(url: String, data: Any?, sendHeader: Boolean, headerMap: Map<String, String>? = null): Any
    suspend fun deleteApi(url: String, data: Any?, sendHeader: Boolean, headerMap: Map<String, String>? = null): Any
    suspend fun uploadFile(
        url: String,
        filePath: String,
        fieldName: String,
        headers: Map<String, String>? = null,
        fields: Map<String, String>? = null,
        method: String = "POST",
        onProgress: ((sent: Int, total: Int) -> Unit)? = null
    ): Any

    suspend fun uploadMultipart(
        url: String,
        headers: Map<String, String>,
        fields: Map<String, String>,
        files: Map<String, String>
    ): Any

    suspend fun downloadFile(
        url: String,
        savePath: String,
        headers: Map<String, String>? = null,
        onProgress: ((received: Int, total: Int) -> Unit)? = null
    ): Any

    fun dispose()
}
