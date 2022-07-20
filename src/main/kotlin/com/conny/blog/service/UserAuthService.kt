package com.conny.blog.service

import com.conny.blog.model.entity.UserEntity
import com.conny.blog.model.request.user.UserAuthRequest
import com.conny.blog.model.response.user.UserLoginResponse
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
interface UserAuthService : UserDetailsService {
    fun register(request: UserAuthRequest): UserEntity?

    fun login(request: UserAuthRequest): UserLoginResponse?
}