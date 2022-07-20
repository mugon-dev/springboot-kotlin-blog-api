package com.conny.blog.model.request.user

import com.conny.blog.model.entity.UserEntity

data class UserAuthRequest(
    var username: String? = null,
    var password: String? = null,
) {
    fun toEntity(): UserEntity {
        return UserEntity(
            username = this.username,
            password = this.password,
        )
    }

}