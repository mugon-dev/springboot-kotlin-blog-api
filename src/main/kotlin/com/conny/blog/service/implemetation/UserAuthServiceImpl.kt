package com.conny.blog.service.implemetation

import com.conny.blog.exception.AlreadyExistsException
import com.conny.blog.exception.UserNotFoundException
import com.conny.blog.exception.auth.InvalidCurrentUserException
import com.conny.blog.model.entity.UserEntity
import com.conny.blog.model.request.user.UserAuthRequest
import com.conny.blog.model.response.user.UserLoginResponse
import com.conny.blog.module.security.UserAuthDetails
import com.conny.blog.repository.RoleRepository
import com.conny.blog.repository.UserRepository
import com.conny.blog.service.UserAuthService
import com.conny.blog.service.UserService
import com.conny.blog.util.TextUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class UserAuthServiceImpl @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
) : UserAuthService {
    override fun getCurrentUser(): UserEntity {
        return getCurrentUserOrNull() ?: throw InvalidCurrentUserException()
    }

    override fun getCurrentUserOrNull(): UserEntity? {
        val authContext = SecurityContextHolder.getContext().authentication ?: return null
        return when (val temp = authContext.details) {
            is UserAuthDetails -> temp.getUser()
            else -> null
        }
    }

    override fun register(request: UserAuthRequest): UserEntity? {
        if (userService.existsByUsername(request.username!!))
            throw AlreadyExistsException("username already exists!")
        val role = roleRepository.queryByNameAndDeletedAtIsNull("USER")
        val data = request.toEntity()
        data.password = TextUtils.encryptedPassword(request.password!!)
        data.roles = mutableListOf(role!!)
        return try {
            userRepository.save(data)
        } catch (ex: Exception) {
            throw Exception(ex)
        }
    }

    override fun login(request: UserAuthRequest): UserLoginResponse? {
        val user = userService.findByUsername(request.username!!)
        if (user != null && TextUtils.validatePassword(request.password!!, user.password!!))
            return UserLoginResponse.toEntity(user)!!
        throw UserNotFoundException("Incorrect username and password")
    }

    override fun loadUserByUsername(username: String?): UserDetails? {
        val user = userService.findByUsername(username!!) ?: return null
        return UserAuthDetails(user)
    }
}