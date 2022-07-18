package com.conny.blog.service

import com.conny.blog.model.entity.CategoryEntity
import org.springframework.stereotype.Service

@Service
interface CategoryService {
    fun create(request: CategoryEntity): CategoryEntity?
}