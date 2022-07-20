package com.conny.blog.module.security.exception

import com.conny.blog.exception.BaseException

class UnsupportedJwtTokenException(
    message: String? = "",
) : BaseException(message)