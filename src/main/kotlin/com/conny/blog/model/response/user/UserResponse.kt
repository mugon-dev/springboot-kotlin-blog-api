package com.conny.blog.model.response.user

import com.conny.blog.model.entity.UserEntity

data class UserResponse(
    var id: Long? = null,
    var username: String? = null,
    var enabled: Boolean? = false,
    var roles: Collection<RoleResponse>? = listOf(),
) {
    companion object {
        fun toEntity(entity: UserEntity?): UserResponse? {
            entity ?: return null

            return UserResponse(
                id = entity.id,
                username = entity.username,
                enabled = entity.enabled,
                roles = entity.roles?.filter { it.deletedAt == null }?.mapNotNull {
                    RoleResponse.toEntity(it)
                }
            )
        }
    }
}