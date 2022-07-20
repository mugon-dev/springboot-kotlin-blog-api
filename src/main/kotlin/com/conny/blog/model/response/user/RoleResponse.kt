package com.conny.blog.model.response.user

import com.conny.blog.model.entity.RoleEntity

data class RoleResponse(
    var id: Long? = null,
    var name: String? = null,
) {
    companion object {
        fun toEntity(entity: RoleEntity?): RoleResponse? {
            entity ?: return null
            return RoleResponse(
                id = entity.id,
                name = entity.name
            )
        }
    }
}