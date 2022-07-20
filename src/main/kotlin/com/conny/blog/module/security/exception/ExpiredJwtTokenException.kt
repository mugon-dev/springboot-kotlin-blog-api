package com.conny.blog.module.security.exception

import com.conny.blog.exception.BaseException

class ExpiredJwtTokenException(
    message: String? = "",
) : BaseException(message)