package com.conny.blog.module.security.exception

import com.conny.blog.exception.BaseException


class JwtNotImplementException(
    message: String? = "User details service not implement yet!"
) : BaseException(message)