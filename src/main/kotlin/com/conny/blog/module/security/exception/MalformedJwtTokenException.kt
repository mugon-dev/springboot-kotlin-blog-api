package com.conny.blog.module.security.exception

import com.conny.blog.exception.BaseException

class MalformedJwtTokenException(
    message: String? = "",
) : BaseException(message)