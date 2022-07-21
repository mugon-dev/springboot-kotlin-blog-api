package com.conny.blog.exception

class ServiceLoaderException(
    message: String? = "application context load failed"
) : BaseException(message)