package com.conny.blog.repository

import com.conny.blog.model.entity.PostEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<PostEntity, Long> {
    fun queryByIdAndDeletedAtIsNull(id: Long): PostEntity
    fun queryByIdAndUserIdAndDeletedAtIsNull(id: Long, userId: Long): PostEntity
    fun queryAllByUserIdAndDeletedAtIsNull(userId: Long, pageable: Pageable): Page<PostEntity>
    fun queryAllByUserIdAndDeletedAtIsNull(userId: Long): MutableList<PostEntity>
    fun queryAllByAndDeletedAtIsNull(): MutableList<PostEntity>

    fun queryAllByDeletedAtIsNull(pageable: Pageable): Page<PostEntity>

    @Query("select p from PostEntity  p where p.category.id = ?1 and p.deletedAt is null")
    fun queryAllByCategoryIdAndDeletedAtIsNull(categoryId: Long, pageable: Pageable): Page<PostEntity>
}