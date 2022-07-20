package com.conny.blog.module.security.exception

import com.conny.blog.exception.BaseException

class SignatureTokenException(
    message: String? = "",
) : BaseException(message)