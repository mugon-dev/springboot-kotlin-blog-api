package com.conny.blog.model.response.user

import com.conny.blog.model.entity.UserEntity
import com.conny.blog.module.security.util.JwtUtils

data class UserLoginResponse(
    var token: String? = null,
) {
    companion object {
        fun toEntity(entity: UserEntity?): UserLoginResponse? {
            entity ?: return null
            val _token = JwtUtils.encryptToken(entity.getUserAuthDetails())

            return UserLoginResponse(
                token = _token
            )
        }
    }
}