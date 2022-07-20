package com.conny.blog.service

import com.conny.blog.model.entity.PostEntity
import com.conny.blog.model.request.PostRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface PostService {
    fun create(request: PostRequest): PostEntity?

    fun update(id: Long, request: PostRequest): PostEntity?

    fun findById(id: Long): PostEntity?

    fun delete(id: Long): PostEntity?

    fun softDelete(id: Long): PostEntity?

    fun findAllAvailable(): MutableList<PostEntity>

    fun findAllAvailable(pageable: Pageable): Page<PostEntity>
    fun findAllAvailable(categoryId: Long, pageable: Pageable): Page<PostEntity>
}