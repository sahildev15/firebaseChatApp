package com.sahil.myapp.apiFunc.networkServices.network

open class ApiException(
    override val message: String,
    val statusCode: Int? = null,
    val data: Any? = null
) : Exception(message) {
    override fun toString(): String = "ApiException: $message (Status: $statusCode)"
}

class InternetException(message: String = "No Internet Connection") :
    ApiException(message)

class RequestTimeoutException(message: String = "Request Timed Out") :
    ApiException(message, 408)

class InvalidUrlException(message: String) :
    ApiException(message, 400)

class FetchDataException(message: String, statusCode: Int? = null) :
    ApiException(message, statusCode)

class UnauthorizedException(message: String = "Unauthorized access") :
    ApiException(message, 401)

class ForbiddenException(message: String = "Access forbidden") :
    ApiException(message, 403)

class NotFoundException(message: String = "Resource not found") :
    ApiException(message, 404)

class ValidationException(message: String) :
    ApiException(message, 422)

class RateLimitException(message: String = "Too many requests") :
    ApiException(message, 429)

class ServerException(message: String, statusCode: Int = 500) :
    ApiException(message, statusCode)

class FileMissingException(message: String) :
    ApiException(message, 404)

class UploadException(message: String) :
    ApiException(message)

class DownloadException(message: String) :
    ApiException(message)

object ErrorHandler {

    fun getErrorMessage(exception: Exception): String {
        return when (exception) {
            is InternetException -> "Please check your internet connection"
            is RequestTimeoutException -> "Request timed out. Please try again"
            is UnauthorizedException -> "Please login again"
            is ForbiddenException -> "You don't have permission to access this resource"
            is NotFoundException -> "Requested resource not found"
            is ValidationException -> "Please check your input data"
            is RateLimitException -> "Too many requests. Please try again later"
            is ServerException -> "Server error. Please try again later"
            is FileMissingException -> "File not found"
            is UploadException -> "File upload failed"
            is DownloadException -> "File download failed"
            is InvalidUrlException, is FetchDataException -> exception.message
            else -> "An unexpected error occurred"
        }
    }

    fun <T> handleError(exception: Exception): ApiResponse<T> {
        val message = getErrorMessage(exception)
        val code = if (exception is ApiException) exception.statusCode else null
        return ApiResponse.error(message, code)
    }

    fun logError(exception: Exception, context: String? = null) {
        val contextInfo = context?.let { "[$it] " } ?: ""
        println("❌ Error: $contextInfo${exception}")
    }

    fun isRecoverableError(exception: Exception): Boolean {
        return when (exception) {
            is InternetException,
            is RequestTimeoutException,
            is ServerException,
            is RateLimitException -> true
            else -> false
        }
    }

    fun getRetryDelay(exception: Exception, retryCount: Int): Long {
        return when (exception) {
            is InternetException -> 2000L * retryCount
            is RequestTimeoutException -> 5000L * retryCount
            is ServerException -> 10000L * retryCount
            is RateLimitException -> 30000L * retryCount
            else -> 5000L
        }
    }
}
