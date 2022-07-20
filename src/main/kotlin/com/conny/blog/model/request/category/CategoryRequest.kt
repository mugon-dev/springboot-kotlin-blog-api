package com.conny.blog.model.request.category

import com.conny.blog.model.entity.CategoryEntity

data class CategoryRequest(
    var name: String? = null,
    var notes: String? = null,
) {
    fun toEntity(): CategoryEntity {
        return CategoryEntity(
            name = this.name,
            notes = this.notes,
        )
    }
}