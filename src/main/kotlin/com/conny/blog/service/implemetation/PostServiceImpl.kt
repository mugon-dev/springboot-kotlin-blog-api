package com.conny.blog.service.implemetation

import com.conny.blog.exception.NotFoundException
import com.conny.blog.model.entity.PostEntity
import com.conny.blog.model.request.post.PostRequest
import com.conny.blog.repository.CategoryRepository
import com.conny.blog.repository.PostRepository
import com.conny.blog.service.PostService
import com.conny.blog.service.UserAuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class PostServiceImpl @Autowired constructor(
    private val postRepository: PostRepository,
    private val categoryRepository: CategoryRepository,
    private val userAuthService: UserAuthService,
) : PostService {
    @Transactional
    override fun create(request: PostRequest): PostEntity? {
        val category = categoryRepository.queryByIdAndDeletedAtIsNull(request.categoryId?.toLong()!!)
            ?: throw NotFoundException("Category not found")

        val user = userAuthService.getCurrentUser()

        val data = request.toEntity()
        data.category = category
        data.user = user
        return try {
            postRepository.save(data)
        } catch (ex: Exception) {
            throw Exception(ex.message)
        }
    }

    override fun update(id: Long, request: PostRequest): PostEntity? {
        val user = userAuthService.getCurrentUser()

        val data = findByIdAndUserId(id, user.id!!)
            ?: throw NotFoundException("Post not found")

        val category = categoryRepository.queryByIdAndDeletedAtIsNull(request.categoryId?.toLong()!!)
            ?: throw NotFoundException("Category not found")


        data.title = request.title ?: data.title
        data.description = request.description ?: data.description
        data.attachment = request.attachment ?: data.attachment
        data.category = category
        data.user = user
        return try {
            postRepository.save(data)
        } catch (ex: Exception) {
            throw Exception(ex.message)
        }
    }

    override fun findById(id: Long): PostEntity? {
        return postRepository.queryByIdAndDeletedAtIsNull(id)
    }

    override fun findByIdAndUserId(id: Long, userId: Long): PostEntity? {
        return postRepository.queryByIdAndUserIdAndDeletedAtIsNull(id, userId)
    }

    override fun delete(id: Long): PostEntity? {
        val user = userAuthService.getCurrentUser()
        val data = findByIdAndUserId(id, user.id!!)
            ?: throw NotFoundException("Post not found")
        try {
            postRepository.deleteById(data.id!!)
        } catch (ex: Exception) {
            throw Exception(ex.message)
        }
        return data
    }

    override fun softDelete(id: Long): PostEntity? {
        val user = userAuthService.getCurrentUser()
        val data = findByIdAndUserId(id, user.id!!)
            ?: throw NotFoundException("Post not found")
        data.deletedAt = Date()
        return try {
            postRepository.save(data)
        } catch (ex: Exception) {
            throw Exception(ex.message)
        }
    }

    override fun findAllAvailable(): MutableList<PostEntity> {
        return postRepository.queryAllByAndDeletedAtIsNull()
    }

    override fun findAllAvailable(pageable: Pageable): Page<PostEntity> {
        return postRepository.queryAllByDeletedAtIsNull(pageable)
    }

    override fun findAllAvailable(categoryId: Long, pageable: Pageable): Page<PostEntity> {
        return postRepository.queryAllByCategoryIdAndDeletedAtIsNull(categoryId, pageable)
    }
}