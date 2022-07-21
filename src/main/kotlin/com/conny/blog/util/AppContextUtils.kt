package com.conny.blog.util

import com.conny.blog.exception.ServiceLoaderException
import org.springframework.context.ApplicationContext

object AppContextUtils {
    private var context: ApplicationContext? = null
    fun setContext(_context: ApplicationContext) = apply {
        this.context = _context
    }

    fun getContext(): ApplicationContext {
        if (this.context == null) throw ServiceLoaderException()
        return this.context!!
    }
}