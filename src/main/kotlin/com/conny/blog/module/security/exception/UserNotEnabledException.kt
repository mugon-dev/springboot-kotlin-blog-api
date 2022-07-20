package com.conny.blog.module.security.exception

import com.conny.blog.exception.BaseException

class UserNotEnabledException(
    message: String? = "",
) : BaseException(message)