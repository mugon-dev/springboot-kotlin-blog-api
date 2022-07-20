package com.conny.blog.service.implemetation

import com.conny.blog.model.entity.UserEntity
import com.conny.blog.repository.UserRepository
import com.conny.blog.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository
) : UserService {
    override fun findById(id: Long): UserEntity? {
        return userRepository.queryByIdAndDeletedAtIsNull(id)
    }

    override fun findByUsername(username: String): UserEntity? {
        return userRepository.queryByUsernameAndDeletedAtIsNull(username)
    }

    override fun existsByUsername(username: String): Boolean {
        return userRepository.existsByUsernameAndDeletedAtIsNull(username)
    }
}