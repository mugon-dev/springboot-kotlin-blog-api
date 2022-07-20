package com.conny.blog.util

import com.conny.blog.module.security.util.JwtUtils

object TextUtils {
    fun encryptedPassword(password: String): String {
        val passwordEncoder = JwtUtils.getPasswordEncoder()
        return passwordEncoder.encode(password)
    }

    fun validatePassword(password: String, encryptedPassword: String): Boolean {
        val passwordEncoder = JwtUtils.getPasswordEncoder()
        return passwordEncoder.matches(password, encryptedPassword)
    }
}