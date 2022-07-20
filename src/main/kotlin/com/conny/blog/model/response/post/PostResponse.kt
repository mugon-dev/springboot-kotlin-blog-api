package com.conny.blog.model.response.post

import com.conny.blog.model.entity.PostEntity

data class PostResponse(
    var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var attachment: String? = null,
) {
    companion object {
        fun toEntity(entity: PostEntity?): PostResponse? {
            entity ?: return null
            return PostResponse(
                id = entity.id,
                title = entity.title,
                description = entity.description,
                attachment = entity.attachment
            )
        }
    }
}