package com.conny.blog.exception.auth

import com.conny.blog.exception.BaseException


class InvalidCurrentUserException(
    message: String? = "Bad credentials!"
) : BaseException(message)
