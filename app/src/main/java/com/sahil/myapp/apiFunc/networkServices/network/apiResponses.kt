package com.sahil.myapp.apiFunc.networkServices.network

data class ApiResponse<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null,
    val statusCode: Int? = null,
    val metadata: Map<String, Any>? = null
) {
    companion object {
        fun <T> loading(message: String? = "Loading...") =
            ApiResponse<T>(Status.LOADING, message = message)

        fun <T> success(
            data: T,
            message: String? = null,
            statusCode: Int? = null,
            metadata: Map<String, Any>? = null
        ) = ApiResponse(Status.SUCCESS, data, message, statusCode, metadata)

        fun <T> error(
            message: String,
            statusCode: Int? = null,
            metadata: Map<String, Any>? = null
        ) = ApiResponse<T>(Status.ERROR, message = message, statusCode = statusCode, metadata = metadata)

        fun <T> idle() = ApiResponse<T>(Status.IDLE)

        fun <T> refreshing(message: String? = "Refreshing...") =
            ApiResponse<T>(Status.REFRESHING, message = message)

        fun <T> uploading(message: String? = "Uploading...") =
            ApiResponse<T>(Status.UPLOADING, message = message)

        fun <T> downloading(message: String? = "Downloading...") =
            ApiResponse<T>(Status.DOWNLOADING, message = message)

        fun <T> loadMore(message: String? = "Loading more...") =
            ApiResponse<T>(Status.LOAD_MORE, message = message)
    }

    val isLoading get() = status == Status.LOADING
    val isSuccess get() = status == Status.SUCCESS
    val isError get() = status == Status.ERROR
    val isIdle get() = status == Status.IDLE
    val isRefreshing get() = status == Status.REFRESHING
    val isUploading get() = status == Status.UPLOADING
    val isDownloading get() = status == Status.DOWNLOADING
    val isLoadMore get() = status == Status.LOAD_MORE
}
