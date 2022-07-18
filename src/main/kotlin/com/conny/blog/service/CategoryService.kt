package com.conny.blog.service

import com.conny.blog.model.entity.CategoryEntity
import com.conny.blog.model.request.CategoryRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface CategoryService {
    fun create(request: CategoryEntity): CategoryEntity?

    fun update(id: Long, request: CategoryRequest): CategoryEntity?

    fun findById(id: Long): CategoryEntity?

    fun delete(id: Long): CategoryEntity?

    fun softDelete(id: Long): CategoryEntity?

    fun findAllAvailable(): MutableList<CategoryEntity>

    fun findAllAvailable(pageable: Pageable): Page<CategoryEntity>
}