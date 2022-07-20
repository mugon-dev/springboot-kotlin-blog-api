package com.conny.blog.property

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppProperties(
    @Value("\${conny.file.path:}")
    private val filePath: String,

    @Value("\${conny.app.api-origin:}")
    private val apiOrigin: String? = null,
) {
    fun getFilePath(): String = this.filePath

    fun getApiOrigin(): String? = apiOrigin
}