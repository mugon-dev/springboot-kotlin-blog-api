package com.conny.blog.module.security.exception

import com.conny.blog.exception.BaseException

class EmptyJwtClaimsException(
    message: String? = "",
) : BaseException(message)