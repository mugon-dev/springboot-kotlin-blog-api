package com.conny.blog.service

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
interface UserAuthService : UserDetailsService {
    fun register()
}