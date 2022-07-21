package com.conny.blog.util

import com.conny.blog.service.UserAuthService
import org.springframework.context.ApplicationContext

object StaticBeanUtils {
    private val context: ApplicationContext
        get() = AppContextUtils.getContext()

    fun getCurrentUser() = context.getBean(UserAuthService::class.java).getCurrentUserOrNull()
}